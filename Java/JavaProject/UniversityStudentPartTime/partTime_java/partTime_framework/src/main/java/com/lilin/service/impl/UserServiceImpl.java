package com.lilin.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.constant.GlobalConstant;
import com.lilin.entity.CollectRecord;
import com.lilin.entity.CompJobInfo;
import com.lilin.entity.User;
import com.lilin.constant.ResponseCodeEnum;
import com.lilin.entity.UserCare;
import com.lilin.exception.CustomizeException;
import com.lilin.mapper.CollectRecordMapper;
import com.lilin.mapper.CompJobInfoMapper;
import com.lilin.mapper.UserCareMapper;
import com.lilin.mapper.UserMapper;
import com.lilin.service.CollectRecordService;
import com.lilin.service.UserService;
import com.lilin.utils.BeanCopyUtils;
import com.lilin.utils.JwtUtil;
import com.lilin.utils.SecurityUtils;
import com.lilin.utils.RedisConnectionUtils;
import com.lilin.vo.AdminUserInfoVo;
import com.lilin.vo.ContactDetailVo;
import com.lilin.vo.LoginUserVo;
import com.lilin.vo.PositionsVo;
import io.jsonwebtoken.lang.Strings;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-09-20 14:00:51
 */
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;
    private final CompJobInfoMapper compJobInfoMapper;
    private final RedisConnectionUtils redisConnectionUtils;
    private final UserCareMapper userCareMapper;
    private final CollectRecordMapper collectRecordMapper;

    public UserServiceImpl(UserMapper userMapper,
                           CompJobInfoMapper compJobInfoMapper,
                           RedisConnectionUtils redisConnectionUtils,
                           UserCareMapper userCareMapper,
                           CollectRecordMapper collectRecordMapper) {
        this.userMapper = userMapper;
        this.compJobInfoMapper = compJobInfoMapper;
        this.redisConnectionUtils = redisConnectionUtils;
        this.userCareMapper = userCareMapper;
        this.collectRecordMapper = collectRecordMapper;
    }

    /**
     * @param user 用户实体
     * @Author lilin
     * @Date 2022/9/20 14:44:14
     * @Return
     * @Description 保存用户信息
     */
    @Override
    public void saveUserInfo(User user) {
        String username = user.getUserName();
        String password = user.getPassword();

        //判断数据是否存在
        if (!StringUtils.hasText(username))
            throw new CustomizeException(ResponseCodeEnum.USERNAME_NOT_FOUND);
        if (!StringUtils.hasText(password))
            throw new CustomizeException(ResponseCodeEnum.PASSWORD_NOT_FOUND);

        //查询用户名，判断用户是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        //如果用户存在，就报错
        if (!Objects.isNull(userMapper.selectOne(queryWrapper))) {
            throw new CustomizeException(ResponseCodeEnum.USER_IS_EXIST);
        }

        //获取盐值
        String salt = SecurityUtils.getSalt();
        //对密码进行加密
        user.setPassword(SecurityUtils.LockPassword(password, salt));

        //补全用户信息
        Date date = new Date();
        user.setCreateBy(username);
        user.setUpdateBy(username);
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setSalt(salt);

        //保存用户信息
        save(user);
    }

    /**
     * @param user    用户实体类
     * @param isAdmin 判断控制层发送的调用是否是后台系统：1 是，0不是
     * @Author lilin
     * @Date 2022/9/21 20:29:26
     * @Return
     * @Description 查询用户信息
     */
    @Override
    public LoginUserVo login(User user, String isAdmin) {
        //1.进行认证处理
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();

        //2.判断是否认证通过
        UsernamePasswordToken upToken = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            subject.login(upToken);
        } catch (CustomizeException e) {
            e.printStackTrace();
        }

        //获取登录用户的 id
        User loginUser = (User) subject.getPrincipal();
        Long userId = loginUser.getId();

        //3.通过 userId 生成 token
        String token = JwtUtil.createJWT(userId.toString());

        //4.将 user 存入 redis
        String redisKey;
        if (GlobalConstant.IS_ADMIN.equals(isAdmin))
            redisKey = GlobalConstant.LOGIN_ADMIN_USER_KEY;
        else
            redisKey = GlobalConstant.LOGIN_USER_KEY;

        redisConnectionUtils.setString(redisKey + userId,
                JSON.toJSONString(loginUser),
                GlobalConstant.JWT_TTL);

        //5.将封装后的用户信息返回
        LoginUserVo loginUserVo = BeanCopyUtils.copyBean(loginUser, LoginUserVo.class);
        loginUserVo.setToken(token);
        return loginUserVo;
    }

    /**
     * @param username 用户名
     * @Author lilin
     * @Date 2022/9/22 10:33:43
     * @Return
     * @Description 根据用户名查询用户
     */
    @Override
    public User selectUserForUsername(String username) {
        Assert.notNull(username, "用户名不能为空");

        //查询用户信息并返回
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User selectUser = userMapper.selectOne(queryWrapper);

        //判断是否查询成功
        if (Objects.isNull(selectUser))
            throw new CustomizeException(ResponseCodeEnum.USER_NOT_EXIST);

        //判断用户是否已经被删除
        if (GlobalConstant.IS_DELETE.equals(selectUser.getDelFlag().toString()))
            throw new CustomizeException(ResponseCodeEnum.USER_IS_DELETE);

        return userMapper.selectOne(queryWrapper);
    }

    /**
     * @param userId 用户 id
     * @Author lilin
     * @Date 2022/9/27 15:46:18
     * @Return
     * @Description 获取用户详情信息
     */
    @Override
    public ContactDetailVo selectUserDetail(Long userId) {
        //判断查询的登录用户是否登录
        User loginUser = SecurityUtils.isUserExists(userId, userMapper);

        //查询用户所发布的职位，封装成 PositionsVo
        LambdaQueryWrapper<CompJobInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CompJobInfo::getContactId, loginUser.getId());
        List<PositionsVo> positionsVos = BeanCopyUtils.copyBeanList(
                compJobInfoMapper.selectList(queryWrapper), PositionsVo.class
        );

        //将以上信息封装成 ContactDetailVo 并返回
        ContactDetailVo contactDetailVo = BeanCopyUtils.copyBean(loginUser, ContactDetailVo.class);
        contactDetailVo.setPositionsVos(positionsVos);

        return contactDetailVo;
    }

    /**
     * @param query    查询内容
     * @param pageNum  分页数量
     * @param pageSize 分页大小
     * @param column   需要模糊查询的字段
     * @Author lilin
     * @Date 2022/10/6 15:45:00
     * @Return
     * @Description 查询用户信息
     */
    @Override
    public List<AdminUserInfoVo> selectUserInfo(String query, Long pageNum, Long pageSize, String column) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //根据用户名模糊查询用户信息
        queryWrapper.like(Strings.hasText(query), column, query);
        //根据创建时间进行逆序查找
        queryWrapper.orderByDesc(GlobalConstant.CREATE_TIME);

        //分页查询用户信息
        IPage<User> page = new Page<>(pageNum, pageSize);
        List<User> userInfoList = userMapper.selectPage(page, queryWrapper).getRecords();

        //如果查询不到数据
        if (userInfoList.isEmpty())
            throw new CustomizeException(ResponseCodeEnum.USER_NOT_EXIST);

        //过滤掉已经被删除掉的用户信息
        userInfoList = userInfoList.stream().filter(
                user -> GlobalConstant.NO_DELETE.equals(user.getDelFlag().toString())
        ).collect(Collectors.toList());

        //将用户列表封装成 vo 类，并将 vo 返回
        return BeanCopyUtils.copyBeanList(userInfoList, AdminUserInfoVo.class);
    }

    /**
     * @Author lilin
     * @Date 2022/10/7 14:36:38
     * @param userId 用户 id
     * @param status 账户状态
     * @Return
     * @Description 修改账户状态：0正常 1停用
     */
    @Override
    public void updateStatus(Long userId, String status) {
        //判断用户是否存在以及是否已经逻辑删除
        User user = userDeleteAndExist(userId, userMapper);

        //修改 status 字段值
        user.setStatus(status);
        //判断是否修改成功
        if (userMapper.updateById(user) != 1)
            throw new CustomizeException(ResponseCodeEnum.UPDATE_STATUS_FAIL);
    }

    /**
     * @Author lilin
     * @Date 2022/10/7 16:09:27
     * @param updateUserData 需要修改的用户实体类
     * @Return
     * @Description 修改用户数据
     */
    @Override
    public void updateUser(User updateUserData) {
        //判断用户是否存在以及是否已经逻辑删除
        userDeleteAndExist(updateUserData.getId(), userMapper);

        //补全用户信息
        updateUserData.setUpdateBy(updateUserData.getUserName());
        updateUserData.setUpdateTime(new Date());
        //获取盐值
        String salt = SecurityUtils.getSalt();
        updateUserData.setSalt(salt);
        updateUserData.setPassword(SecurityUtils.LockPassword(updateUserData.getPassword(), salt));

        //更新用户数据
        //判断是否更新成功
        if (userMapper.updateById(updateUserData) != 1)
            throw new CustomizeException(ResponseCodeEnum.UPDATE_USER_FAIL);
    }

    /**
     * @Author lilin
     * @Date 2022/10/7 17:22:55
     * @param userId 用户 id
     * @Return
     * @Description 删除用户数据
     */
    @Override
    public void deleteUser(Long userId) {
        //判断用户是否存在
        User selectUser = SecurityUtils.isUserExists(userId, userMapper);
        if (Objects.isNull(selectUser))
            throw new CustomizeException(ResponseCodeEnum.USER_NOT_EXIST);

        //判断用户是否是超级管理员
        if (GlobalConstant.USER_TYPE_ADMIN.equals(selectUser.getType()))
            //超级管理员不可删除
            throw new CustomizeException(ResponseCodeEnum.SUPER_ADMIN_IS_NOT_DELETE);

        //删除用户数据
        //判断是否删除成功
        if (userMapper.deleteById(selectUser) != 1)
            throw new CustomizeException(ResponseCodeEnum.DELETE_USER_FAIL);

        //对与用户表有深度关联的表进行删除操作
        //用户关注表
        userCareMapper.delete(new LambdaQueryWrapper<UserCare>().eq(UserCare::getCareId, userId));
        //用户收藏表
        collectRecordMapper.delete(new LambdaQueryWrapper<CollectRecord>().eq(CollectRecord::getUserId, userId));
    }

    /**
     * @Author lilin
     * @Date 2022/10/12 16:50:25
     * @param token token
     * @Return
     * @Description 退出登录
     */
    @Override
    public void logout(String token) {
        //检验用户是否登录
        User loginUser = null;
        try {
            loginUser = SecurityUtils.isUserLogin(token, redisConnectionUtils);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除 redis 中储存的 登录用户 信息
        //判断是否删除成功
        if (
                !redisConnectionUtils.deleteKey(GlobalConstant.LOGIN_ADMIN_USER_KEY +
                        loginUser.getId())
        )
            throw new CustomizeException(ResponseCodeEnum.LOGOUT_FAIL);

    }

    /**
     * @Author lilin
     * @Date 2022/10/7 16:18:25
     * @param userId 用户 id
     * @param userMapper userMapper
     * @Return
     * @Description 判断用户是否存在以及是否以及逻辑删除
     */
    private User userDeleteAndExist(Long userId, UserMapper userMapper) {
        //确定账户存在
        User user = SecurityUtils.isUserExists(userId, userMapper);
        if (Objects.isNull(user))
            throw new CustomizeException(ResponseCodeEnum.USER_NOT_EXIST);

        //判断用户是否已经被删除
        if (GlobalConstant.IS_DELETE.equals(user.getDelFlag().toString()))
            throw new CustomizeException(ResponseCodeEnum.USER_IS_DELETE);

        return user;
    }
}


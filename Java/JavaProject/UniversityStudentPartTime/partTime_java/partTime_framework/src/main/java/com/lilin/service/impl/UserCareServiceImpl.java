package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.constant.GlobalConstant;
import com.lilin.constant.ResponseCodeEnum;
import com.lilin.entity.User;
import com.lilin.entity.UserCare;
import com.lilin.exception.CustomizeException;
import com.lilin.mapper.UserCareMapper;
import com.lilin.mapper.UserMapper;
import com.lilin.service.UserCareService;
import com.lilin.utils.BeanCopyUtils;
import com.lilin.utils.RedisConnectionUtils;
import com.lilin.utils.SecurityUtils;
import com.lilin.vo.CareDetailVo;
import com.lilin.vo.UserCareDetailVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by LiLin on 2022/10/6/15:54:52
 */
@Service("UserCareService")
public class UserCareServiceImpl extends ServiceImpl<UserCareMapper, UserCare> implements UserCareService {
    private final UserCareMapper userCareMapper;
    private final UserMapper userMapper;
    private final RedisConnectionUtils redisConnectionUtils;

    public UserCareServiceImpl(UserCareMapper userCareMapper,
                           RedisConnectionUtils redisConnectionUtils,
                           UserMapper userMapper) {
        this.userCareMapper = userCareMapper;
        this.redisConnectionUtils = redisConnectionUtils;
        this.userMapper = userMapper;
    }

    /**
     * @Author lilin
     * @Date 2022/9/28 14:02:28
     * @param token token
     * @Return
     * @Description 获取登录用户关注的用户 id 列表
     */
    @Override
    public List<Long> getUserCares(String token) {
        //获取登录用户信息
        User loginUser = null;
        try {
            loginUser = SecurityUtils.isUserLogin(token, redisConnectionUtils);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询登录用户所关注的用户列表
        LambdaQueryWrapper<UserCare> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserCare::getUserId, Objects.requireNonNull(loginUser).getId());
        List<UserCare> userCares = userCareMapper.selectList(queryWrapper);

        //保证查询的用户列表不为空
        if (userCares.isEmpty())
            throw new CustomizeException(ResponseCodeEnum.CARE_LIST_IS_EMPTY);

        //排除关注列表中已被删除的关注
        //将关注的用户列表的 id 返回
        return userCares.stream().filter(
                userCare -> GlobalConstant.NO_DELETE.equals(userCare.getIsDelete())
        ).map(UserCare::getCareId).collect(Collectors.toList());
    }

    /**
     * @Author lilin
     * @Date 2022/9/28 16:23:11
     * @param careId 要关注的用户 id
     * @param token token
     * @param isDelete 是否删除当前关注的用户
     * @Return
     * @Description 是否关注用户
     */
    @Override
    public void isCare(Long careId, String token, String isDelete) {
        //获取登录用户信息
        User loginUser = null;
        try {
            loginUser = SecurityUtils.getLoginUserForToken(redisConnectionUtils, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //确定登录用户是否存在
        if (Objects.isNull(loginUser))
            throw new CustomizeException(ResponseCodeEnum.PLEASE_TO_LOGIN);

        //确定要关注的用户是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, careId);
        if (Objects.isNull(userMapper.selectOne(queryWrapper)))
            throw new CustomizeException(ResponseCodeEnum.USER_NOT_EXIST);

        //根据 careId 和 userId查询关注信息
        Long userId = loginUser.getId();
        LambdaQueryWrapper<UserCare> userCareQueryWrapper = new LambdaQueryWrapper<>();
        userCareQueryWrapper.eq(UserCare::getCareId, careId);
        userCareQueryWrapper.eq(UserCare::getUserId, userId);
        UserCare userCare = userCareMapper.selectOne(userCareQueryWrapper);

        //判断关注信息是否存在
        String userName = loginUser.getUserName();
        if (!Objects.isNull(userCare)) {
            //存在，修改关注信息的 isDelete 值
            if (isDeleteCareForExist(userName, userId, careId, isDelete) == 0)
                throw new CustomizeException(ResponseCodeEnum.UPDATE_CARE_FAIL);
            return;
        }

        //不存在，添加关注信息到数据库
        //封装 UserCare 实体类
        userCare = new UserCare();
        Date date = new Date();
        userCare.setId(null);
        userCare.setUserId(userId);
        userCare.setCareId(careId);
        userCare.setCollectUser(loginUser.getType());
        userCare.setIsDelete(GlobalConstant.NO_DELETE);
        userCare.setCreateBy(userName);
        userCare.setUpdateBy(userName);
        userCare.setCreateTime(date);
        userCare.setUpdateTime(date);

        //添加关注信息到数据库
        int insert = userCareMapper.insert(userCare);

        //确定添加成功
        if (insert == 0)
            throw new CustomizeException(ResponseCodeEnum.ADD_CARE_FAIL);
    }

    /**
     * @Author lilin
     * @Date 2022/9/29 13:12:22
     * @param token token
     * @Return
     * @Description 获取登录用户关注的用户列表
     */
    @Override
    public List<UserCareDetailVo> getUserCareDetails(String token) {
        //确定用户登录
        User loginUser = null;
        try {
            loginUser = SecurityUtils.isUserLogin(token, redisConnectionUtils);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomizeException(ResponseCodeEnum.PLEASE_TO_LOGIN);
        }

        //查询登录用户所关注的用户列表
        LambdaQueryWrapper<UserCare> userCareWrapper = new LambdaQueryWrapper<>();
        userCareWrapper.eq(UserCare::getUserId, Objects.requireNonNull(loginUser).getId());
        userCareWrapper.orderByDesc(UserCare::getUpdateTime);
        List<UserCare> userCares = userCareMapper.selectList(userCareWrapper);

        //判断是否查询成功
        if (userCares.isEmpty())
            throw new CustomizeException(ResponseCodeEnum.CARE_LIST_IS_EMPTY);

        //根据查询出来的 关注用户id，查询用户的详细信息
        List<User> userList = userCares.stream().map(
                userCare -> userMapper.selectOne(
                        new LambdaQueryWrapper<User>().eq(
                                User::getId, userCare.getCareId()
                        )
                )
        ).collect(Collectors.toList());

        //将查询到的用户信息封装返回
        return BeanCopyUtils.copyBeanList(userList, UserCareDetailVo.class);
    }

    /**
     * @Author lilin
     * @Date 2022/10/9 14:05:49
     * @Return
     * @Description 获取所有的关注信息
     */
    @Override
    public List<CareDetailVo> getAllCares() {
        //获取所有关注
        List<UserCare> userCareList = userCareMapper.selectList(new QueryWrapper<>());

        //封装为 vo 类
        //将数据返回
        return getCareDetailVoList(userCareList);
    }

    /**
     * @Author lilin
     * @Date 2022/10/9 16:09:22
     * @param id 关注表id
     * @param isDelete 是否删除
     * @Return
     * @Description 修改用户关注表
     */
    @Override
    public void updateCare(Long id, String isDelete) {
        //判断当前关注是否存在
        UserCare userCare = userCareMapper.selectOne(new LambdaQueryWrapper<UserCare>().eq(UserCare::getId, id));
        if (Objects.isNull(userCare))
            throw new CustomizeException(ResponseCodeEnum.USER_CARE_IS_NOT_EXIST);

        //更新 userCare 中的数据
        userCare.setIsDelete(isDelete);
        userCare.setUpdateBy(GlobalConstant.ADMIN);
        userCare.setUpdateTime(new Date());

        //修改关注表
        //确定修改成功
        if (userCareMapper.updateById(userCare) != 1)
            throw new CustomizeException(ResponseCodeEnum.UPDATE_CARE_FAIL);
    }

    /**
     * @Author lilin
     * @Date 2022/10/9 14:43:36
     * @param userCareList userCareList
     * @Return
     * @Description 将查询出来的 userCareList 封装成对应的 vo 类
     */
    private List<CareDetailVo> getCareDetailVoList(List<UserCare> userCareList) {
        List<CareDetailVo> careDetailVoList = new ArrayList<>();

        for (UserCare userCare: userCareList) {
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, userCare.getUserId()));
            User careUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, userCare.getCareId()));
            CareDetailVo careDetailVo = BeanCopyUtils.copyBean(userCare, CareDetailVo.class);
            careDetailVo.setUserName(user.getUserName());
            careDetailVo.setCareUserName(careUser.getUserName());
            careDetailVoList.add(careDetailVo);
        }

        return careDetailVoList;
    }

    /**
     * @Author lilin
     * @Date 2022/9/28 16:40:06
     * @param username 用户名
     * @param userId 用户 id
     * @param careId 关注的用户 id
     * @param isDelete 是否关注：0 关注，1 取消关注
     * @Return
     * @Description 在 关注信息 存在的情况下，选择取消或者 关注信息
     */
    private int isDeleteCareForExist(String username, Long userId, Long careId, String isDelete) {
        UpdateWrapper<UserCare> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(GlobalConstant.IS_DELETE_, isDelete).eq(GlobalConstant.USER_ID, userId).eq(GlobalConstant.CARE_ID, careId);
        updateWrapper.set(GlobalConstant.UPDATE_BY, username).eq(GlobalConstant.USER_ID, userId).eq(GlobalConstant.CARE_ID, careId);
        return userCareMapper.update(null, updateWrapper);
    }
}

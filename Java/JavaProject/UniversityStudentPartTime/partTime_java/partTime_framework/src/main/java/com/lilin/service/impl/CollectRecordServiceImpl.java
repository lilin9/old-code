package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.constant.GlobalConstant;
import com.lilin.constant.ResponseCodeEnum;
import com.lilin.entity.CollectRecord;
import com.lilin.entity.CompJobInfo;
import com.lilin.entity.User;
import com.lilin.exception.CustomizeException;
import com.lilin.mapper.CollectRecordMapper;
import com.lilin.mapper.CompJobInfoMapper;
import com.lilin.mapper.UserMapper;
import com.lilin.service.CollectRecordService;
import com.lilin.utils.BeanCopyUtils;
import com.lilin.utils.JwtUtil;
import com.lilin.utils.RedisConnectionUtils;
import com.lilin.utils.SecurityUtils;
import com.lilin.vo.CollectDetailVo;
import com.lilin.vo.CollectRecordVo;
import com.lilin.vo.PositionsVo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 收藏夹 服务实现类
 *
 * @author makejava
 * @since 2022-09-26 09:16:54
 */
@Service
public class CollectRecordServiceImpl extends ServiceImpl<CollectRecordMapper, CollectRecord> implements CollectRecordService {

    private final CollectRecordMapper collectRecordMapper;
    private final RedisConnectionUtils redisConnectionUtils;
    private final CompJobInfoMapper compJobInfoMapper;
    private final UserMapper userMapper;

    /*
    通过构造器实现依赖注入
     */
    public CollectRecordServiceImpl(CollectRecordMapper collectRecordMapper,
                                    RedisConnectionUtils redisConnectionUtils,
                                    CompJobInfoMapper compJobInfoMapper,
                                    UserMapper userMapper) {
        this.collectRecordMapper = collectRecordMapper;
        this.redisConnectionUtils = redisConnectionUtils;
        this.compJobInfoMapper = compJobInfoMapper;
        this.userMapper = userMapper;
    }

    /**
     * @Author lilin
     * @Date 2022/9/26 09:32:39
     * @param token token
     * @Return
     * @Description 查询用户收藏夹
     */
    @Override
    public List<CollectRecordVo> selectUserCollect(String token) {
        //根据 token 获取登录的 用户id
        String userId = null;
        try {
            userId = JwtUtil.parseJWT(SecurityUtils.parseToken(token)).getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //通过 用户id 在 redis 查询 登录用户信息是否存在
        User loginUser = null;
        try {
            loginUser = SecurityUtils.getLoginUserForToken(redisConnectionUtils, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //不存在抛出异常
        if (Objects.isNull(loginUser))
            throw new CustomizeException(ResponseCodeEnum.USER_NOT_EXIST);

        //根据 用户id 查询用户是否有收藏夹信息
        LambdaQueryWrapper<CollectRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CollectRecord::getUserId, userId);
        List<CollectRecord> collectRecords = collectRecordMapper.selectList(queryWrapper);

        //不存在抛出异常
        if (collectRecords.isEmpty())
            throw new CustomizeException(ResponseCodeEnum.COLLECT_LIST_IS_EMPTY);

        //过滤掉 collectRecords 中 isDelete=1 的字段
        collectRecords = collectRecords.stream()
                .filter(collectRecord -> GlobalConstant.NO_DELETE.equals(collectRecord.getIsDelete()))
                .collect(Collectors.toList());

        //将收藏夹信息封装成 vo 返回
        return BeanCopyUtils.copyBeanList(collectRecords, CollectRecordVo.class);
    }

    /**
     * @Author lilin
     * @Date 2022/9/26 13:37:04
     * @param jobId 职位id
     * @param token token
     * @param isDelete 是否取消收藏：1 取消，0 收藏
     * @Return
     * @Description 是否取消收藏当前职位
     */
    @Override
    public void isCollect(Long jobId, String token, String isDelete) {
        //获取当前登录用户信息
        User loginUser = null;
        try {
            loginUser = SecurityUtils.getLoginUserForToken(redisConnectionUtils, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //判断是否存在
        if (Objects.isNull(loginUser))
            throw new CustomizeException(ResponseCodeEnum.PLEASE_TO_LOGIN);

        //查询要收藏的职位信息是否是存在的
        LambdaQueryWrapper<CompJobInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CompJobInfo::getId, jobId);
        CompJobInfo selectCompJobInfo = compJobInfoMapper.selectOne(lambdaQueryWrapper);
        if (Objects.isNull(selectCompJobInfo))
            throw new CustomizeException(ResponseCodeEnum.POSITIONS_NOT_EXIST);

        //如果要收藏的职位是用户自己发布的职位，就抛出异常
        Long userId = loginUser.getId();
        if (userId.equals(selectCompJobInfo.getContactId()))
            throw new CustomizeException(ResponseCodeEnum.COLLECT_FAIL);

        //查询 收藏表 中 userId 和 jobId 是否存在
        LambdaQueryWrapper<CollectRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CollectRecord::getJobId, jobId);
        queryWrapper.eq(CollectRecord::getUserId, userId);

        //如果当前用户的收藏存在
        String username = loginUser.getUserName();
        if (!Objects.isNull(collectRecordMapper.selectOne(queryWrapper))) {
            //如果修改失败
            if (!isDeleteCollectForExist(jobId, isDelete, username))
                throw new CustomizeException(ResponseCodeEnum.UPDATE_COLLECT_FAIL);
            return;
        }

        //封装 CollectRecord 实体类
        CollectRecord collectRecord = new CollectRecord();
        Date date = new Date();
        collectRecord.setUserId(userId);
        collectRecord.setJobId(jobId);
        collectRecord.setCollectUser(loginUser.getType());
        collectRecord.setIsDelete(GlobalConstant.NO_DELETE);

        collectRecord.setCreateBy(username);
        collectRecord.setUpdateBy(username);
        collectRecord.setCreateTime(date);
        collectRecord.setUpdateTime(date);

        //添加用户收藏
        boolean isSave = save(collectRecord);


        //是否添加成功
        if (!isSave)
            throw new CustomizeException(ResponseCodeEnum.ADD_COLLECT_FAIL);
    }

    /**
     * @Author lilin
     * @Date 2022/9/29 10:40:50
     * @param token token
     * @Return
     * @Description 获取用户收藏
     */
    @Override
    public List<PositionsVo> getUserCollects(String token) {
        //确定用户登录
        User loginUser = null;
        try {
            loginUser = SecurityUtils.isUserLogin(token, redisConnectionUtils);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询用户收藏列表
        LambdaQueryWrapper<CollectRecord> collectRecordWrapper = new LambdaQueryWrapper<>();
        collectRecordWrapper.eq(CollectRecord::getUserId, Objects.requireNonNull(loginUser).getId());
        collectRecordWrapper.eq(CollectRecord::getIsDelete, GlobalConstant.NO_DELETE);
        collectRecordWrapper.orderByDesc(CollectRecord::getUpdateTime);
        List<CollectRecord> collectRecords = collectRecordMapper.selectList(collectRecordWrapper);

        //判断查询结果是否为空
        if (collectRecords.isEmpty())
            throw new CustomizeException(ResponseCodeEnum.COLLECT_LIST_IS_EMPTY);

        //根据收藏夹信息中工作 id 查询工作详情信息
        List<CompJobInfo> compJobInfoList = collectRecords.stream().map(
                collectRecord -> compJobInfoMapper.selectOne(
                        new LambdaQueryWrapper<CompJobInfo>().eq(
                                CompJobInfo::getId, collectRecord.getJobId()
                        )
                )
        ).collect(Collectors.toList());

        //将查询结果封装返回
        return BeanCopyUtils.copyBeanList(compJobInfoList, PositionsVo.class);
    }

    /**
     * @Author lilin
     * @Date 2022/10/8 14:19:31
     * @Return
     * @Description 获取所有收藏信息
     */
    @Override
    public List<CollectDetailVo> getAllCollects() {
        //查询所有收藏信息
        //将查询结果返回
        return collectRecordMapper.getAllCollects();
    }

    /**
     * @Author lilin
     * @Date 2022/10/6 15:45:00
     * @param query 查询内容
     * @param pageNum 分页数量
     * @param pageSize 分页大小
     * @Return
     * @Description 查询收藏信息
     */
    @Override
    public List<CollectDetailVo> searchCollect(String query, Long pageNum, Long pageSize) {
        //根据用户名查询用户信息，得到用户id
        List<User> userList = userMapper.selectList(new LambdaQueryWrapper<User>().like(User::getUserName, query));

        //如果查询到了用户信息列表
        if (!userList.isEmpty())
            //就根据 用户id 查询收藏列表
            return collectRecordMapper.getAllCollectsByUserIds(
                    //将用户列表的 id 提取出来
                    userList.stream().map(
                            User::getId
                    ).collect(Collectors.toList())
            );

        //如果查不到，就查询职位表
        List<Long> jobIdList = compJobInfoMapper
                .selectList(
                        new LambdaQueryWrapper<CompJobInfo>().like(CompJobInfo::getJobTitle, query)
                )
                //将职位列表的 id 提取出来
                .stream().map(CompJobInfo::getId).collect(Collectors.toList());

        //根据查询到的 职位id 查询收藏信息
        return collectRecordMapper.getAllCollectsByJobIds(jobIdList);
    }

    /**
     * @Author lilin
     * @Date 2022/10/9 10:53:00
     * @param updateCollectData 需要修改的职位收藏信息
     * @Return
     * @Description 修改用户收藏信息
     */
    @Override
    public void updateCollect(CollectDetailVo updateCollectData) {
        //根据 id 查询收藏是否真实存在
        CollectRecord selectCollect = collectRecordMapper.selectOne(
                new LambdaQueryWrapper<CollectRecord>().eq(
                        CollectRecord::getId, updateCollectData.getId()
                )
        );
        if (Objects.isNull(selectCollect))
            throw new CustomizeException(ResponseCodeEnum.COLLECT_IS_NOT_EXIST);

        //将 updateCollectData 封装成 CollectRecord 实体类
        selectCollect.setIsDelete(updateCollectData.getIsDelete());
        selectCollect.setUpdateBy(GlobalConstant.ADMIN);
        selectCollect.setUpdateTime(new Date());


        //修改用户收藏
        //判断是否修改成功
        if (collectRecordMapper.updateById(selectCollect) != 1)
            throw new CustomizeException(ResponseCodeEnum.UPDATE_COLLECT_FAIL);
    }

    /**
     * @Author lilin
     * @Date 2022/10/9 13:11:21
     * @param collectId 收藏 id
     * @Return
     * @Description 根据 id 删除用户收藏
     */
    @Override
    public void deleteCollectById(Long collectId) {
        //判断用户收藏是否存在
        if (Objects.isNull(collectRecordMapper.selectOne(
                new LambdaQueryWrapper<CollectRecord>().eq(CollectRecord::getId, collectId)
        )))
            throw new CustomizeException(ResponseCodeEnum.COLLECT_IS_NOT_EXIST);

        //删除收藏
        int result = collectRecordMapper.delete(
                new LambdaQueryWrapper<CollectRecord>().eq(CollectRecord::getId, collectId)
        );

        //判断是否删除成功
        if (result != 1)
            throw new CustomizeException(ResponseCodeEnum.DELETE_COLLECT_FAIL);
    }

    /**
     * @Author lilin
     * @Date 2022/9/27 13:43:17
     * @param jobId 职位 id
     * @param isDelete 是否取消收藏：1 取消收藏，0 收藏
     * @param username 用户名
     * @Return
     * @Description 在收藏存在的情况下，选择取消或者收藏职位
     */
    private boolean isDeleteCollectForExist(Long jobId, String isDelete, String username) {
        UpdateWrapper<CollectRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_delete", isDelete).eq("job_id", jobId);
        updateWrapper.set("update_by", username).eq("job_id", jobId);

        return update(updateWrapper);
    }
}


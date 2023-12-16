package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lilin.constant.GlobalConstant;
import com.lilin.constant.ResponseCodeEnum;
import com.lilin.entity.CollectRecord;
import com.lilin.entity.CompJobInfo;
import com.lilin.entity.User;
import com.lilin.exception.CustomizeException;
import com.lilin.mapper.CollectRecordMapper;
import com.lilin.mapper.CompJobInfoMapper;
import com.lilin.mapper.UserMapper;
import com.lilin.service.PositionsService;
import com.lilin.utils.BeanCopyUtils;
import com.lilin.utils.RedisConnectionUtils;
import com.lilin.utils.SecurityUtils;
import com.lilin.vo.PositionsDetailVo;
import com.lilin.vo.PositionsVo;
import com.lilin.vo.PublisherVo;
import com.lilin.vo.listVo.PositionsDetailListVo;
import io.jsonwebtoken.lang.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 职位信息 服务实现类
 *
 * @author makejava
 * @since 2022-09-24 11:13:16
 */
@Service
public class PositionsServiceImpl implements PositionsService {
    private final CompJobInfoMapper compJobInfoMapper;
    private final UserMapper userMapper;
    private final RedisConnectionUtils redisConnectionUtils;
    private final CollectRecordMapper collectRecordMapper;

    /*
    通过构造器实现依赖注入
     */
    public PositionsServiceImpl(CompJobInfoMapper compJobInfoMapper,
                                UserMapper userMapper,
                                RedisConnectionUtils redisConnectionUtils,
                                CollectRecordMapper collectRecordMapper) {
        this.compJobInfoMapper = compJobInfoMapper;
        this.userMapper = userMapper;
        this.redisConnectionUtils = redisConnectionUtils;
        this.collectRecordMapper = collectRecordMapper;
    }

    /**
     * @Author lilin
     * @Date 2022/9/24 15:46:00
     * @Return
     * @Description 获取所有职位信息，当没有参数时
     */
    @Override
    public List<PositionsDetailVo> getAllPositions() {
        //查询所有职位信息，并封装成 PositionsDetailVo
        return copyCompJobInfoToPositionsDetailVo(compJobInfoMapper.selectList(new LambdaQueryWrapper<>()));
    }

    /**
     * @Author lilin
     * @Date 2022/9/24 15:46:00
     * @param query 查询内容
     * @param pageNum 分页数量
     * @param pageSize 分页大小
     * @Return
     * @Description 获取所有职位信息，当有参数时
     */
    @Override
    public PositionsDetailListVo getAllPositions(String query, Long pageNum, Long pageSize) {
        //分页查询
        //先通过 job_title 查询
        Page<CompJobInfo> compJobInfoPage = selectCompJobInfosByQuery(GlobalConstant.JOB_TITLE, query, pageNum, pageSize);

        //如果查询的数据为空
        if (compJobInfoPage.getRecords().isEmpty()) {
            //就通过 job_cate 查询
            compJobInfoPage = selectCompJobInfosByQuery(GlobalConstant.JOB_CATE, query, pageNum, pageSize);
            //如果依旧为空
            if (compJobInfoPage.getRecords().isEmpty())
                //就通过 job_detail_place 查询
                compJobInfoPage = selectCompJobInfosByQuery(GlobalConstant.JOB_DETAIL_PLACE, query, pageNum, pageSize);
        }

        //封装成 vo，并返回
        return new PositionsDetailListVo(
                copyCompJobInfoToPositionsDetailVo(compJobInfoPage.getRecords()),
                compJobInfoPage.getTotal()
        );
    }

    /**
     * @Author lilin
     * @Date 2022/9/25 14:50:26
     * @param id 工作 id
     * @Return
     * @Description 获取工作详情信息
     */
    @Override
    public PositionsDetailVo getPositionsDetail(Long id) {
        //查询职位详细信息
        LambdaQueryWrapper<CompJobInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CompJobInfo::getId, id);
        CompJobInfo compJobInfo = compJobInfoMapper.selectOne(queryWrapper);

        //判断职位是否存在
        if (Objects.isNull(compJobInfo))
            throw new CustomizeException(ResponseCodeEnum.POSITIONS_NOT_EXIST);

        //查询联系人信息
        LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(User::getId, compJobInfo.getContactId());
        User userInfo = userMapper.selectOne(queryWrapper1);

        //判断是否成功查询到用户
        if (Objects.isNull(userInfo))
            throw new CustomizeException(ResponseCodeEnum.USER_NOT_EXIST);

        //封装成 vo 类并返回
        PositionsDetailVo positionsDetailVo = BeanCopyUtils.copyBean(compJobInfo, PositionsDetailVo.class);
        positionsDetailVo.setPublisher(BeanCopyUtils.copyBean(userInfo, PublisherVo.class));
        return positionsDetailVo;
    }

    /**
     * @Author lilin
     * @Date 2022/9/29 15:43:26
     * @param searchContent 搜索内容
     * @param column 查找的字段名
     * @Return
     * @Description 根据用户输入的搜索内容查询职位信息
     */
    @Override
    public List<PositionsDetailVo> getPositions(String column, String searchContent) {
        //判断参数是否为空
        if (!StringUtils.hasText(searchContent))
            throw new CustomizeException(ResponseCodeEnum.SEARCH_CONTENT_IS_EMPTY);

        //根据参数模糊查询职位信息
        QueryWrapper<CompJobInfo> compJobInfoQueryWrapper = new QueryWrapper<>();
        compJobInfoQueryWrapper.like(column, searchContent);
        List<CompJobInfo> compJobInfoList = compJobInfoMapper.selectList(compJobInfoQueryWrapper);

        //判断是否查询成功
        if (compJobInfoList.isEmpty())
            return null;

        List<PositionsDetailVo> positionsDetailVos = new ArrayList<>();
        for (CompJobInfo compJobInfo: compJobInfoList) {
            //将查询出来的职位信息封装成 PositionsDetailVo
            PositionsDetailVo positionsDetailVo = BeanCopyUtils.copyBean(compJobInfo, PositionsDetailVo.class);

            //根据查询出来的职位信息，查询联系人信息
            LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
            userQueryWrapper.eq(User::getId, compJobInfo.getContactId());
            //将联系人信息封装成 PublisherVo
            PublisherVo publisherVo = BeanCopyUtils.copyBean(userMapper.selectOne(userQueryWrapper), PublisherVo.class);

            //将 publisherVo 加入 PositionsDetailVo 中
            positionsDetailVo.setPublisher(publisherVo);

            positionsDetailVos.add(positionsDetailVo);
        }

        //将 positionsDetailVos 返回
        return positionsDetailVos;
    }

    /**
     * @Author lilin
     * @Date 2022/10/2 17:19:20
     * @param token token
     * @param compJobInfo 职位实体类
     * @Return
     * @Description 添加职位信息
     */
    @Override
    public void addPositions(String token, CompJobInfo compJobInfo) {
        //先判断 compJobInfo 是否有数据
        if (Objects.isNull(compJobInfo))
            throw new CustomizeException(ResponseCodeEnum.COMP_JOB_INFO_IS_NULL);

        //判断用户是否登录
        User loginUser = null;
        try {
            loginUser = SecurityUtils.getLoginUserForToken(redisConnectionUtils, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.isNull(loginUser))
            throw new CustomizeException(ResponseCodeEnum.PLEASE_TO_LOGIN);

        //补全职位信息
        Date date = new Date();
        compJobInfo.setIsAvailable(GlobalConstant.IS_AVAILABLE);
        String username = loginUser.getUserName();
        compJobInfo.setContactId(loginUser.getId());
        compJobInfo.setCreateBy(username);
        compJobInfo.setUpdateBy(username);
        compJobInfo.setCreateTime(date);
        compJobInfo.setUpdateTime(date);

        //将职位信息添加到数据库
        int insert = compJobInfoMapper.insert(compJobInfo);

        //判断是否添加成功
        if (insert == 0)
            throw new CustomizeException(ResponseCodeEnum.ADD_COMP_JOB_INFO_FAIL);

    }

    /**
     * @Author lilin
     * @Date 2022/9/29 14:44:45
     * @param userId 用户 id
     * @Return
     * @Description 获取用户发布的职位信息
     */
    @Override
    public List<PositionsVo> getPublishPositions(Long userId) {
        //确定用户是否存在
        SecurityUtils.isUserExists(userId, userMapper);

        //根据 userId 查询用户发布的职位 id
        LambdaQueryWrapper<CompJobInfo> compJobInfoWrapper = new LambdaQueryWrapper<>();
        compJobInfoWrapper.eq(CompJobInfo::getContactId, userId);
        compJobInfoWrapper.orderByDesc(CompJobInfo::getUpdateTime);
        List<CompJobInfo> compJobInfoList = compJobInfoMapper.selectList(compJobInfoWrapper);

        //判断查询出来的职位信息是否为空
        if (compJobInfoList.isEmpty())
            throw new CustomizeException(ResponseCodeEnum.COMP_JOB_INFO_LIST_EMPTY);

        //将查询到的职位信息封装返回
        return BeanCopyUtils.copyBeanList(compJobInfoList, PositionsVo.class);
    }

    /**
     * @Author lilin
     * @Date 2022/10/11 16:08:02
     * @param positionsData 职位信息数据
     * @param userName 用户名
     * @Return
     * @Description 添加职位信息（后台专属）
     */
    @Override
    public void addPositionsForAdmin(CompJobInfo positionsData, String userName) {
        //先根据用户名查询用户信息
        Long userId = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, userName))
                //得到用户 id
                .getId();

        //补全 positionsData数据
        positionsData.setContactId(userId);
        positionsData.setIsAvailable(GlobalConstant.IS_AVAILABLE);
        positionsData.setCreateBy(GlobalConstant.ADMIN);
        positionsData.setUpdateBy(GlobalConstant.ADMIN);
        Date date = new Date();
        positionsData.setCreateTime(date);
        positionsData.setUpdateTime(date);

        //将 职位数据 添加到数据库中
        //判断是否添加成功
        if (compJobInfoMapper.insert(positionsData) != 1)
            throw new CustomizeException(ResponseCodeEnum.ADD_COMP_JOB_INFO_FAIL);
    }

    /**
     * @Author lilin
     * @Date 2022/10/12 13:49:20
     * @param positionsId 职位id
     * @param isAvailable 是否启用
     * @Return
     * @Description 修改职位信息
     */
    @Override
    public void updatePositions(Long positionsId, Integer isAvailable) {
        //查看职位是否存在
        CompJobInfo selectPositions = compJobInfoMapper.selectOne(new LambdaQueryWrapper<CompJobInfo>().eq(
                CompJobInfo::getId, positionsId
        ));
        if (Objects.isNull(selectPositions))
            throw new CustomizeException(ResponseCodeEnum.SEARCH_POSITIONS_FAIL);

        //更新 selectPositions 实体类
        selectPositions.setIsAvailable(isAvailable);
        selectPositions.setUpdateBy(GlobalConstant.ADMIN);
        selectPositions.setUpdateTime(new Date());

        //修改数据库
        //判断是否修改成功
        if (compJobInfoMapper.updateById(selectPositions) != 1)
            throw new CustomizeException(ResponseCodeEnum.UPDATE_POSITIONS_FAIL);
    }

    @Override
    public void deletePositions(Long positionsId) {
        //查看职位是否存在
        CompJobInfo selectPositions = compJobInfoMapper.selectOne(new LambdaQueryWrapper<CompJobInfo>().eq(
                CompJobInfo::getId, positionsId
        ));
        if (Objects.isNull(selectPositions))
            throw new CustomizeException(ResponseCodeEnum.SEARCH_POSITIONS_FAIL);

        //物理删除职位信息
        //判断是否删除成功
        if (compJobInfoMapper.deleteById(positionsId) != 1)
            throw new CustomizeException(ResponseCodeEnum.DELETE_POSITIONS_FAIL);
        //删除用户收藏表中的职位信息
        if (collectRecordMapper.delete(new LambdaQueryWrapper<CollectRecord>().eq(CollectRecord::getJobId, positionsId)) != 1)
            throw new CustomizeException(ResponseCodeEnum.DELETE_POSITIONS_FAIL);
    }

    /**
     * @Author lilin
     * @Date 2022/10/10 16:49:20
     * @param query 查询内容
     * @param pageNum 分页数量
     * @param pageSize 分页大小
     * @Return
     * @Description 对查询职位信息操作的逻辑封装
     */
    private Page<CompJobInfo> selectCompJobInfosByQuery(String column, String query, Long pageNum, Long pageSize) {
        QueryWrapper<CompJobInfo> queryWrapper = new QueryWrapper<>();
        //根据参数模糊查询
        queryWrapper.like(Strings.hasText(query), column, query);
        //根据创建时间排序
        queryWrapper.orderByDesc(GlobalConstant.CREATE_TIME);

        //分页查询
        return compJobInfoMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
    }

    /**
     * @Author lilin
     * @Date 2022/10/10 16:16:22
     * @Return
     * @Description 将 CompJobInfo 封装成 PositionsDetailVo 的工具类
     */
    private List<PositionsDetailVo> copyCompJobInfoToPositionsDetailVo(List<CompJobInfo> compJobInfos) {
        //根据查询出来的职位信息中的联系人 id，到 user 表中查询联系人头像和姓名
        List<PositionsDetailVo> positionsDetailVoList = new ArrayList<>();

        for (CompJobInfo compJobInfo: compJobInfos) {
            PositionsDetailVo positionsDetailVo = BeanCopyUtils.copyBean(compJobInfo, PositionsDetailVo.class);

            //到用户表中查询 联系人 信息
            LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(User::getId, compJobInfo.getContactId());

            //如果查不到联系人信息，就舍弃这条职位信息
            User selectUser = userMapper.selectOne(lambdaQueryWrapper);
            if (Objects.isNull(selectUser))
                break;

            PublisherVo companyVo = BeanCopyUtils.copyBean(selectUser, PublisherVo.class);

            //将查询到的 联系人信息 更新到 positionVo 中
            positionsDetailVo.setPublisher(companyVo);

            //将封装成功的 positionVo 添加到数组中
            positionsDetailVoList.add(positionsDetailVo);
        }

        //将数据返回
        return positionsDetailVoList;
    }
}


package com.tianmao.service.impl;

import com.tianmao.mapper.CollectionMapper;
import com.tianmao.mapper.UserMapper;
import com.tianmao.pojo.Collection;
import com.tianmao.pojo.User;
import com.tianmao.service.CollectionService;
import com.tianmao.service.exception.*;
import com.tianmao.vo.CollectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/05/17/12:52
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionMapper collectionMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * @Author LiLin
     * @Date 2022/5/17 11:52
     * @Param uid 用户id
     * @Param pid 商品id
     * @Description 保存收藏夹到数据库
     */
    @Override
    public void insertToCollection(Integer uid, Integer pid) {
        //首先判断用户登录是否异常
        User user = userMapper.selectUserByUid(uid);
        if (user == null)
            //为空抛出异常
            throw new UserNotFoundException("用户不存在");

        //判断收藏夹是否已经存在此商品
        if (collectionMapper.selectCollectionByPid(pid) != null)
            throw new CollectionDuplicateException("收藏夹已存在此商品");

        //往收藏夹添加商品
        Collection collection = new Collection();
        collection.setUid(uid);
        collection.setPid(pid);

        String username = user.getUsername();
        collection.setCreatedUser(username);
        collection.setModifiedUser(username);

        Date date = new Date();
        collection.setCreatedTime(date);
        collection.setModifiedTime(date);
        Integer rows = collectionMapper.insertToCollection(collection);

        //判断是否添加成功
        if (rows != 1)
            throw new InsertException("添加收藏夹出现未知异常");
    }

    /**
     * @Author LiLin
     * @Date 2022/5/17 15:22
     * @Param uid 用户id
     * @return 返回查询到的收藏夹商品的vo对象集合
     * @Description 查询所有的收藏夹商品信息
     */
    @Override
    public List<CollectionVo> selectCollectionVoByUid(Integer uid) {
        //判断uid用户是否存在
        if (userMapper.selectUserByUid(uid) == null)
            throw new UserNotFoundException("用户不存在");

        //查询收藏夹数据
        List<CollectionVo> list = collectionMapper.selectCollectionVoByUid(uid);
        //将不属于uid的收藏夹数据从列表中移除
        list.removeIf(item -> !uid.equals(item.getUid()));
        return list;
    }

    /**
     * @Author lilin
     * @Date 2022/5/31 14:06:54
     * @param pid 商品id
     * @Return 返回查询到的收藏夹信息
     * @Description 查询收藏夹信息
     */
    @Override
    public Collection selectCollection(Integer pid) {
        return collectionMapper.selectCollectionByPid(pid);
    }

    /**
     * @Author LiLin
     * @Date 2022/5/17 21:46
     * @Param coid 收藏夹id
     * @Param uid 用户id
     * @Description 根据coid删除收藏夹
     */
    @Override
    public void deleteCollection(Integer pid, Integer uid) {
        //查询用户是否存在
        if (userMapper.selectUserByUid(uid) == null)
            throw new UserNotFoundException("用户不存在");

        //查询收藏夹是否存在
        if (collectionMapper.selectCollectionByPid(pid) == null)
            throw new CollectionNotFoundException("收藏夹商品不存在");

        //删除收藏夹商品
        Integer rows = collectionMapper.deleteCollectionByPid(pid);
        //判断是否删除失败
        if (rows != 1)
            throw new DeleteException("删除收藏夹出现未知异常");
    }
}

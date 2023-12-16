package com.tianmao.service;


import com.tianmao.pojo.Collection;
import com.tianmao.vo.CollectionVo;

import java.util.List;

/**
 * Created by LiLin on 2022/05/17/12:46
 *
 * 收藏夹service层
 */
public interface CollectionService {
    /**
     * @Author LiLin
     * @Date 2022/5/17 11:52
     * @Param uid 用户id
     * @Param pid 商品id
     * @Description 保存收藏夹到数据库
     */
    void insertToCollection(Integer uid, Integer pid);

    /**
     * @Author LiLin
     * @Date 2022/5/17 15:22
     * @Param uid 用户id
     * @return 返回查询到的收藏夹商品的vo对象集合
     * @Description 查询所有的收藏夹商品信息
     */
    List<CollectionVo> selectCollectionVoByUid(Integer uid);

    /**
     * @Author lilin
     * @Date 2022/5/31 14:06:54
     * @param pid 商品id
     * @Return 返回查询到的收藏夹信息
     * @Description 查询收藏夹信息
     */
    Collection selectCollection(Integer pid);

    /**
     * @Author LiLin
     * @Date 2022/5/17 21:46
     * @Param pid 商品id
     * @Param uid 用户id
     * @Description 根据coid删除收藏夹
     */
    void deleteCollection(Integer pid, Integer uid);
}

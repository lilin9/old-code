package com.tianmao.mapper;

import com.tianmao.pojo.Collection;
import com.tianmao.vo.CollectionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by LiLin on 2022/05/17/11:50
 *
 * 收藏夹
 */
@Mapper
public interface CollectionMapper {
    /**
     * @Author LiLin
     * @Date 2022/5/17 11:51
     * @Param pid 商品id
     * @return 返回查询到的收藏夹
     * @Description 根据商品id查询收藏夹
     */
    Collection selectCollectionByPid(Integer pid);

    /**
     * @Author LiLin
     * @Date 2022/5/17 11:52
     * @Param collection 收藏夹实体类
     * @return 返回影响的行数
     * @Description 保存收藏夹到数据库
     */
    Integer insertToCollection(Collection collection);

    /**
     * @Author LiLin
     * @Date 2022/5/17 15:22
     * @Param uid 用户id
     * @return 返回查询到的收藏夹商品的vo对象集合
     * @Description 根据用户id查询所有的收藏夹商品信息
     */
    List<CollectionVo> selectCollectionVoByUid(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/5/17 21:24
     * @Param pid 商品id
     * @return 返回影响的行数
     * @Description 删除收藏夹
     */
    Integer deleteCollectionByPid(Integer pid);

    /**
     * @Author LiLin
     * @Date 2022/5/17 21:51
     * @Param coid 收藏夹id
     * @return 返回查询到的收藏夹数据
     * @Description 根据coid查询收藏夹
     */
    Collection selectCollectionByCoid(Integer coid);
}

package com.tianmao.mapper;

import com.tianmao.pojo.Cart;
import com.tianmao.vo.CartVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/05/12/16:28
 *
 * 购物车
 */
@Mapper
public interface CartMapper {
    /**
     * @Author LiLin
     * @Date 2022/5/12 16:42
     * @Param uid 用户id
     * @return 返回查询到的商品信息组成的vo对象列表
     * @Description 根据用户id查询购物车所有商品信息
     */
    List<CartVo> selectCartVoByUid(Integer uid);

    /**
     * @Author LiLin
     * @Date 2022/5/12 20:18
     * @Param cid 购物车id
     * @return 返回查询到的购物车商品数据
     * @Description 根据cid查询购物车商品信息
     */
    Cart selectCartByCid(Integer cid);

    /**
     * @Author LiLin
     * @Date 2022/5/12 20:32
     * @Param cid 购物车id
     * @Param num 修改的数量
     * @Param modifiedUser 修改人
     * @Param modifiedTime 修改时间
     * @return 返回影响到的行数
     * @Description 根据cid更新购物车商品数量
     */
    Integer updateCartNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * @Author LiLin
     * @Date 2022/5/13 14:55
     * @Param cid 购物车id
     * @return 返回影响的行数
     * @Description 根据cid删除购物车数据
     */
    Integer deleteCartByCid(Integer cid);

    /**
     * @Author lilin
     * @Date 2022/5/30 13:48:06
     * @param cart  购物车实体
     * @Return 返回影响的行数
     * @Description 添加商品到购物车
     */
    Integer insert(Cart cart);
}

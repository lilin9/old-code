package com.tianmao.mapper;

import com.tianmao.pojo.Order;
import com.tianmao.pojo.OrderItem;
import com.tianmao.vo.CartVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by LiLin on 2022/05/16/11:26
 *
 * 订单
 */
@Mapper
public interface OrderMapper {
    /**
     * @Author LiLin
     * @Date 2022/5/16 11:28
     * @Param pids 商品id的集合
     * @return 返回查询到的 CartVo 列表
     * @Description 根据cids查询购物车商品数据
     */
    List<CartVo> selectCartVoByCids(List<Integer> pids);

    /**
     * @Author LiLin
     * @Date 2022/5/18 19:50
     * @Param order 订单实体
     * @return 返回影响的行数
     * @Description 往订单表插入订单
     */
    Integer insertOrder(Order order);

    /**
     * @Author LiLin
     * @Date 2022/5/18 19:51
     * @Param orderItem 订单单项
     * @return 返回影响的行数
     * @Description 往订单单项表插入订单单项
     */
    Integer insertOrderItem(OrderItem orderItem);
}

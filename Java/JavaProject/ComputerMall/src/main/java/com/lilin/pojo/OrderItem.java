package com.lilin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/04/19/15:04
 *
 * 订单项实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity {
    private Integer id;     //订单中的商品记录的id
    private Integer oid;    //所归属的订单的id
    private Integer pid;    //商品的id
    private String title;   //商品标题
    private String image;   //商品图片
    private Long price;     //商品价格
    private Integer num;    //购买数量
}

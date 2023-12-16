package com.lilin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/04/15/11:27
 *
 * 购物车实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {
    private Integer cid;    //购物车数据id
    private Integer uid;    //用户id
    private Integer pid;    //商品id
    private Long price;     //加入商品时的单价
    private Integer num;    //商品数量
}

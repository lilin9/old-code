package com.tianmao.vo;

import com.tianmao.pojo.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by LiLin on 2022/05/12/16:35
 *
 * 购物车的 VO 对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVo implements Serializable {
    private Integer pid;        //商品id
    private Integer cid;        //购物车id
    private Integer uid;        //用户id
    private Integer num;        //商品数量
    private Long price;         //加入商品时的单价
    private String title;       //商品标题
    private String image;       //商品图片路径
    private Long realPrice;     //商品的真实价格
}

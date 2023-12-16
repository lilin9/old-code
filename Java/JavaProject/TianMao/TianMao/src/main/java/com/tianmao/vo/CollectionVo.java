package com.tianmao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by LiLin on 2022/05/17/15:18
 *
 * 收藏夹Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionVo implements Serializable {
    private Integer coid;   //收藏夹id
    private Integer pid;    //商品id
    private Integer uid;    //用户id
    private String image;   //商品图片路径
    private String title;   //商品名称
    private Long price;     //商品价格
}

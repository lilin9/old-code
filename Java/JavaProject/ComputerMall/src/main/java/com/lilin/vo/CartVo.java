package com.lilin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by LiLin on 2022/04/15/20:37
 *
 * 购物车VO对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVo implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private String image;
    private Long realPrice;
}

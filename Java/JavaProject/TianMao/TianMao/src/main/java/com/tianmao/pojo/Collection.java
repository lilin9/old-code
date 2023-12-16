package com.tianmao.pojo;

import com.tianmao.pojo.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/05/17/11:42
 *
 * 收藏夹实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection extends BaseEntity {
    private Integer coid;   //收藏夹id
    private Integer uid;    //用户id
    private Integer pid;    //商品id
}

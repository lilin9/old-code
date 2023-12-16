package com.lilin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/04/14/14:37
 *
 * 商品类别
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class productCategory extends BaseEntity {
    private Integer id;             //主键
    private Integer parentId;       //父分类id
    private String name;            //名称
    private Integer status;         //状态   1：正常   0：删除
    private Integer sortOrder;      //排序号
    private Integer isParent;       //是否是父分类   1：是  0：否
}

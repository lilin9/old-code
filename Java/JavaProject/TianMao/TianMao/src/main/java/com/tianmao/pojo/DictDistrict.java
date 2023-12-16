package com.tianmao.pojo;

import com.tianmao.pojo.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/05/24/9:01
 *
 * 中国省市区列表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictDistrict extends BaseEntity {
    private Integer id;
    private String parent;
  private String code;
  private String name;
}

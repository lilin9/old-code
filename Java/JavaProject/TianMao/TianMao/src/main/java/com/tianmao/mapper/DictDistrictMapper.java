package com.tianmao.mapper;

import com.tianmao.pojo.DictDistrict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by LiLin on 2022/05/24/9:06
 *
 * 省市区列表
 */
@Mapper
public interface DictDistrictMapper {
    /**
     * @Author LiLin
     * @Date 2022/5/24 9:06
     * @Param parent 父代码
     * @return 返回查询到的省市区列表信息
     * @Description 根据父代码查询省市区信息
     */
    List<DictDistrict> selectDictDistrictByParent(String parent);

    /**
     * @Author LiLin
     * @Date 2022/5/24 9:44
     * @Param code 代码编号
     * @return 返回查询到的名称
     * @Description 根据代码编号查询省市区名称
     */
    String selectNameByCode(String code);
}

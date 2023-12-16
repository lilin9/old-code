package com.lilin.mapper;

import com.lilin.pojo.District;

import java.util.List;

/**
 * Created by LiLin on 2022/04/12/13:59
 */
public interface DistrictMapper {
    /**
     * @Author LiLin
     * @Date 2022/4/12 14:04
     * @Param parent 父区域的代码号
     * @return 返回一个 district 类型的列表
     * @Description 根据父区域的代码号查询区域信息
     */
    List<District> selectDistrictByParent(String parent);

    /**
     * @Author LiLin
     * @Date 2022/4/12 15:46
     * @Param code 省市区编号
     * @return 返回省市区的名字
     * @Description 根据code查询省市区名字
     */
    String selectNameByCode(String code);
}

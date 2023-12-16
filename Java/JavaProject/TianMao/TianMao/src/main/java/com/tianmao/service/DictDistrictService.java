package com.tianmao.service;

import com.tianmao.pojo.DictDistrict;

import java.util.List;

/**
 * Created by LiLin on 2022/05/24/9:19
 *
 * 省市区列表
 */
public interface DictDistrictService {
    /**
     * @Author LiLin
     * @Date 2022/4/12 14:27
     * @Param parent 父区域的编号
     * @return 返回list集合，类型是 DictDistrict
     * @Description 根据父区域编号查询区域信息
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

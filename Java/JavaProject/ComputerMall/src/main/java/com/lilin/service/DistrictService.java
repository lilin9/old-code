package com.lilin.service;

import com.lilin.pojo.District;

import java.util.List;

/**
 * Created by LiLin on 2022/04/12/14:26
 *
 * 获取省市区信息的接口类
 */
public interface DistrictService {
    /**
     * @Author LiLin
     * @Date 2022/4/12 14:27
     * @Param parent 父区域的编号
     * @return 返回list集合，类型是 District
     * @Description 根据父区域编号查询区域信息
     */
    List<District> selectDistrictByParent(String parent);

    /**
     * @Author LiLin
     * @Date 2022/4/12 15:59
     * @Param code 省市区的编号
     * @return 返回查询到的省市区的名字
     * @Description 根据code查询省市区的名字
     */
    String selectNameByCode(String code);
}

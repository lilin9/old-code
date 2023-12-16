package com.lilin.service.impl;

import com.lilin.mapper.DistrictMapper;
import com.lilin.pojo.District;
import com.lilin.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiLin on 2022/04/12/14:28
 *
 * 获取省市区信息的接口类的实现类
 */
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictMapper districtMapper;

    /**
     * @Author LiLin
     * @Date 2022/4/12 14:27
     * @Param parent 父区域的编号
     * @return 返回list集合，类型是 District
     * @Description 根据父区域编号查询区域信息
     */
    @Override
    public List<District> selectDistrictByParent(String parent) {
        List<District> list = districtMapper.selectDistrictByParent(parent);

        //在进行网络数据传输时，为了尽量避免无效数据的，可以将无效数据设置为null
        //这样一方面可以节省流量，另一方面可以提高效率
        for (District each : list) {
            each.setId(null);
            each.setParent(null);
        }
        return list;
    }

    /**
     * @Author LiLin
     * @Date 2022/4/12 15:59
     * @Param code 省市区的编号
     * @return 返回查询到的省市区的名字
     * @Description 根据code查询省市区的名字
     */
    @Override
    public String selectNameByCode(String code) {
        return districtMapper.selectNameByCode(code);
    }
}

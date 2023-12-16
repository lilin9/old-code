package com.tianmao.service.impl;

import com.tianmao.mapper.DictDistrictMapper;
import com.tianmao.pojo.DictDistrict;
import com.tianmao.service.DictDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiLin on 2022/05/24/9:20
 */
@Service
public class DictDistrictServiceImpl implements DictDistrictService {
    @Autowired
    DictDistrictMapper dictDistrictMapper;

    /**
     * @Author LiLin
     * @Date 2022/4/12 14:27
     * @Param parent 父区域的编号
     * @return 返回list集合，类型是 DictDistrict
     * @Description 根据父区域编号查询区域信息
     */
    @Override
    public List<DictDistrict> selectDictDistrictByParent(String parent) {

        List<DictDistrict> list = dictDistrictMapper.selectDictDistrictByParent(parent);

        //在进行网络数据传输时，为了尽量避免无效数据的，可以将无效数据设置为null
        //这样一方面可以节省流量，另一方面可以提高效率
        for (DictDistrict each : list) {
            each.setId(null);
            each.setParent(null);
        }
        return list;
    }

    /**
     * @Author LiLin
     * @Date 2022/5/24 9:44
     * @Param code 代码编号
     * @return 返回查询到的名称
     * @Description 根据代码编号查询省市区名称
     */
    @Override
    public String selectNameByCode(String code) {
        return dictDistrictMapper.selectNameByCode(code);
    }
}

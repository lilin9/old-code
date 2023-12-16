package com.tianmao.mapper;

import com.tianmao.pojo.DictDistrict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by LiLin on 2022/05/24/9:14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DictDistrictMapperTests {
    @Autowired
    DictDistrictMapper dictDistrictMapper;

    @Test
    public void selectDictDistrictByParent() {
        List<DictDistrict> list = dictDistrictMapper.selectDictDistrictByParent("120100");
        list.forEach(System.out :: println);
    }

    @Test
    public void selectNameByCode() {
        System.out.println(dictDistrictMapper.selectNameByCode("120105"));
    }
}

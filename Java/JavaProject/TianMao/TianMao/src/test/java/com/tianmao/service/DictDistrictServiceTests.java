package com.tianmao.service;

import com.tianmao.pojo.DictDistrict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by LiLin on 2022/05/24/9:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DictDistrictServiceTests {
    @Autowired
    DictDistrictService dictDistrictService;

    @Test
    public void selectDictDistrictByParent() {
        List<DictDistrict> list = dictDistrictService.selectDictDistrictByParent("120100");
        list.forEach(System.out :: println);
    }

    @Test
    public void selectNameByCode() {
        System.out.println(dictDistrictService.selectNameByCode("120105"));
    }
}

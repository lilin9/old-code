package com.lilin.service;

import com.lilin.pojo.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by LiLin on 2022/04/12/14:38
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    @Autowired
    DistrictService districtService;

    @Test
    public void selectDistrictByParent() {
        List<District> list = districtService.selectDistrictByParent("86");
        list.forEach(System.out :: println);
    }

    @Test
    public void selectNameByCode() {
        System.out.println(districtService.selectNameByCode("450300"));
    }
}

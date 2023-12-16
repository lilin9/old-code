package com.lilin.mapper;

import com.lilin.pojo.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by LiLin on 2022/04/12/14:16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    @Autowired
    DistrictMapper districtMapper;

    @Test
    public void selectDistrictByParent() {
        List<District> list = districtMapper.selectDistrictByParent("450300");
        for (District each: list) {
            System.out.println(each);
        }
    }

    @Test
    public void selectNameByCode() {
        System.out.println(districtMapper.selectNameByCode("450300"));
    }
}

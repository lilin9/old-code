package com.lilin.controller;

import com.lilin.pojo.District;
import com.lilin.service.DistrictService;
import com.lilin.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LiLin on 2022/04/12/14:56
 */
@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {
    @Autowired
    DistrictService districtService;

    @GetMapping({"/", ""})
    public JsonResult<List<District>> selectDistrict(String parent) {
        return new JsonResult<>(OK, districtService.selectDistrictByParent(parent));
    }
}

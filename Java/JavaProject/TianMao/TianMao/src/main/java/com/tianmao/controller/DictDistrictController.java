package com.tianmao.controller;

import com.tianmao.pojo.DictDistrict;
import com.tianmao.service.DictDistrictService;
import com.tianmao.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LiLin on 2022/05/24/9:27
 *
 * 省市区列表
 */
@RestController
@RequestMapping("dictDistrict")
public class DictDistrictController extends BaseController {
    @Autowired
    DictDistrictService dictDistrictService;

    @GetMapping({"", "/"})
    public JsonResult<List<DictDistrict>> getDictDistrict(String parent) {
        return new JsonResult<>(OK, dictDistrictService.selectDictDistrictByParent(parent));
    }

    @GetMapping("getName")
    public JsonResult<String> getName(String code) {
        return new JsonResult<>(OK, dictDistrictService.selectNameByCode(code));
    }
}

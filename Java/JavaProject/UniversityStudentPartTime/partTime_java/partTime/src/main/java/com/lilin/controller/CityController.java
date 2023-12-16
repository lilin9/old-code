package com.lilin.controller;

import com.lilin.service.CityService;
import com.lilin.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (City)表控制层
 *
 * @author makejava
 * @since 2022-09-24 13:45:55
 */
@RestController
@RequestMapping("city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * @Author lilin
     * @Date 2022/9/24 13:49:01
     * @Return
     * @Description 获取所有热门城市
     */
    @GetMapping("/")
    public ResponseResult<Object> hotCity() {
        return ResponseResult.success(cityService.getAllHotCity());
    }
}


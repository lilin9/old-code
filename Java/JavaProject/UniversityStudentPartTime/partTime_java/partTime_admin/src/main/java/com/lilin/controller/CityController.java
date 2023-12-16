package com.lilin.controller;

import com.lilin.constant.ResponseCodeEnum;
import com.lilin.entity.City;
import com.lilin.exception.CustomizeException;
import com.lilin.service.CityService;
import com.lilin.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * (City)表控制层
 *
 * @author makejava
 * @since 2022-10-09 16:18:02
 */
@RestController
@RequestMapping("city")
public class CityController {
    private final CityService cityService;
    /*
    通过构造器实现依赖注入
    */
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * @Author lilin
     * @Date 2022/10/9 16:48:02
     * @param query 搜索内容
     * @param pageNum 分页数量
     * @param pageSize 分页大小
     * @Return
     * @Description 获取城市数据信息
     */
    @GetMapping("/")
    public ResponseResult<Object> getCityInfo(String query, Integer pageNum, Integer pageSize) {
        return ResponseResult.success(cityService.getCityInfo(query, pageNum, pageSize));
    }

    /**
     * @Author lilin
     * @Date 2022/10/10 10:37:22
     * @param cityData 城市实体类
     * @Return
     * @Description 添加城市信息
     */
    @PostMapping("/")
    public ResponseResult<Object> addCity(@RequestBody City cityData) {
        //确保参数不为空
        if (Objects.isNull(cityData))
            throw new CustomizeException(ResponseCodeEnum.PARAMS_IS_NULL);

        cityService.addCity(cityData);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/10/10 13:37:18
     * @param cityId 城市id
     * @Return
     * @Description 删除城市
     */
    @DeleteMapping("/{cityId}")
    public ResponseResult<Object> deleteCity(@PathVariable("cityId") Long cityId) {
        cityService.deleteCity(cityId);
        return ResponseResult.success();
    }
}


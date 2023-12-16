package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.entity.City;
import com.lilin.vo.listVo.CityListVo;

import java.util.List;

/**
 * (City)表服务接口
 *
 * @author makejava
 * @since 2022-09-24 11:13:15
 */
public interface CityService extends IService<City> {

    List<City> getAllHotCity();

    CityListVo getCityInfo(String query, Integer pageNum, Integer pageSize);

    void addCity(City cityData);

    void deleteCity(Long cityId);
}


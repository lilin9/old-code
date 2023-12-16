package com.MrLi.demo.service;

import com.MrLi.demo.bean.City;
import com.MrLi.demo.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MrLi on 2022/03/28/11:27
 */@Service
public class CityService {
     @Autowired
    CityMapper cityMapper;

     public City selectAllById(Integer id) {
         if (id != null && id != 0) return cityMapper.selectAllById(id);
         return null;
     }

     public int insertCity(City city) {
         if (city != null) return cityMapper.insertCity(city);
         return 0;
     }
}

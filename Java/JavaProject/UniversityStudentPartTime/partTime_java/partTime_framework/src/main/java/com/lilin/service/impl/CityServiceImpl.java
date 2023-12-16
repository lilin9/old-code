package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.constant.GlobalConstant;
import com.lilin.constant.ResponseCodeEnum;
import com.lilin.entity.City;
import com.lilin.exception.CustomizeException;
import com.lilin.mapper.CityMapper;
import com.lilin.service.CityService;
import com.lilin.utils.BeanCopyUtils;
import com.lilin.vo.listVo.CityListVo;
import com.lilin.vo.CityVo;
import io.jsonwebtoken.lang.Strings;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * (City)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 11:13:16
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {
    private final CityMapper cityMapper;
    public CityServiceImpl(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    /**
     * @Author lilin
     * @Date 2022/9/24 13:49:01
     * @Return
     * @Description 获取所有热门城市
     */
    @Override
    public List<City> getAllHotCity() {
        LambdaQueryWrapper<City> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(City::getId, City::getCity);

        return list(queryWrapper);
    }

    /**
     * @Author lilin
     * @Date 2022/10/9 16:48:02
     * @Return
     * @Description 获取城市数据信息
     */
    @Override
    public CityListVo getCityInfo(String query, Integer pageNum, Integer pageSize) {
        //根据搜索内容查询城市数据
        LambdaQueryWrapper<City> wrapper = new LambdaQueryWrapper<>();
        //根据创建时间进行排序
        wrapper.orderByDesc(City::getCreateTime);
        wrapper.like(Strings.hasText(query), City::getCity, query);

        //分页查询
        Page<City> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);

        //将查询出来的数据封装成 vo
        return new CityListVo(
                BeanCopyUtils.copyBeanList(page.getRecords(), CityVo.class),
                page.getTotal()
        );
    }

    /**
     * @Author lilin
     * @Date 2022/10/10 10:37:22
     * @param cityData 城市实体类
     * @Return
     * @Description 添加城市信息
     */
    @Override
    public void addCity(City cityData) {
        //根据 城市名 判断城市是不是已经存在
        if (!Objects.isNull(
                cityMapper.selectOne(new LambdaQueryWrapper<City>().eq(City::getCity, cityData.getCity()))
        ))
            throw new CustomizeException(ResponseCodeEnum.CITY_NAME_IS_NOT_EMPTY);

        //根据 城市id 判断 id 是否重复
        if (!Objects.isNull(
                cityMapper.selectOne(new LambdaQueryWrapper<City>().eq(City::getCityId, cityData.getCityId()))
        ))
            throw new CustomizeException(ResponseCodeEnum.CITY_ID_IS_NOT_EMPTY);

        //将实体类信息补全
        Date date = new Date();
        cityData.setCreateTime(date);
        cityData.setUpdateTime(date);
        cityData.setCreateBy(GlobalConstant.ADMIN);
        cityData.setUpdateBy(GlobalConstant.ADMIN);

        //添加城市信息到数据库中
        if (cityMapper.insert(cityData) != 1)
            //判断是否添加成功
            throw new CustomizeException(ResponseCodeEnum.ADD_CITY_FAIL);
    }

    /**
     * @Author lilin
     * @Date 2022/10/10 13:37:18
     * @param cityId 城市id
     * @Return
     * @Description 删除城市
     */
    @Override
    public void deleteCity(Long cityId) {
        //查询城市是否存在
        if (Objects.isNull(cityMapper.selectById(cityId)))
            throw new CustomizeException(ResponseCodeEnum.CITY_IS_NULL);

        //删除城市
        //判断是否删除成功
        if (cityMapper.deleteById(cityId) != 1)
            throw new CustomizeException(ResponseCodeEnum.DELETE_CITY_FAIL);
    }
}


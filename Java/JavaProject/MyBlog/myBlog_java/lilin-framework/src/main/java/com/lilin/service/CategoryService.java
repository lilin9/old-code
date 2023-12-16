package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.entity.Category;
import com.lilin.vo.CategoryVo;

import java.util.List;

/**
 * Created by LiLin on 2022/9/5/10:43:02
 */
public interface CategoryService extends IService<Category> {
    List<CategoryVo> getCategoryList();

    List<CategoryVo> listAllCategory();
}

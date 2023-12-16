package com.lilin.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.lilin.entity.Category;
import com.lilin.enums.AppHttpCodeEnum;
import com.lilin.service.CategoryService;
import com.lilin.utils.CopyBeanUtils;
import com.lilin.utils.ResponseResult;
import com.lilin.utils.WebUtils;
import com.lilin.vo.CategoryVo;
import com.lilin.vo.ExcelCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by LiLin on 2022/9/15/10:18:59
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * @Author lilin
     * @Date 2022/9/15 10:54:00
     * @Return
     * @Description 查询所有分类接口
     */
    @GetMapping("/listAllCategory")
    public ResponseResult<Object> listAllCategory() {
        List<CategoryVo> categoryVoList = categoryService.listAllCategory();
        return ResponseResult.okResult(categoryVoList);
    }

    @GetMapping("/export")
    @PreAuthorize("@ps.hasPermission('content:category:export')")
    public void export(HttpServletResponse response) {
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx", response);

            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();

            //把数据写入到 excel 中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("分类导出")
                    .doWrite(CopyBeanUtils.copyBeanList(categoryVos, ExcelCategoryVo.class));
        } catch (Exception e) {
            e.printStackTrace();
            //如果出现异常，需要响应到 json 中
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }
}
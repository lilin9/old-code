package com.lilin.controller;

import com.lilin.dto.TagListDto;
import com.lilin.service.TagService;
import com.lilin.utils.ResponseResult;
import com.lilin.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LiLin on 2022/9/12/16:19:04
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    public ResponseResult<Object> list() {
        return ResponseResult.okResult(tagService.list());
    }

    /**
     * @Author lilin
     * @Date 2022/9/14 19:35:00
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param tagListDto 标签名和备注实体类
     * @Return
     * @Description 查询标签列表
     */
    @GetMapping("/list")
    public ResponseResult<Object> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return ResponseResult.okResult(tagService.getPageTagList(pageNum, pageSize, tagListDto));
    }

    /**
     * @Author lilin
     * @Date 2022/9/15 11:02:28
     * @Return
     * @Description 查询所有标签
     */
    @GetMapping("/listAllTag")
    public ResponseResult<Object> listAllTag() {
        List<TagVo> tagVoList = tagService.listAllTag();
        return ResponseResult.okResult(tagVoList);
    }
}
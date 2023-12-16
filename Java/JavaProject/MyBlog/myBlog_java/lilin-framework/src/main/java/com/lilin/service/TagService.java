package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.dto.TagListDto;
import com.lilin.entity.Tag;
import com.lilin.vo.TagVo;

import java.util.List;

/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2022-09-12 16:03:05
 */
public interface TagService extends IService<Tag> {
    Object getPageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    List<TagVo> listAllTag();
}


package com.lilin.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.dto.TagListDto;
import com.lilin.entity.Tag;
import com.lilin.mapper.TagMapper;
import com.lilin.service.TagService;
import com.lilin.utils.CopyBeanUtils;
import com.lilin.utils.ResponseResult;
import com.lilin.vo.PageVo;
import com.lilin.vo.TagVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2022-09-12 16:03:05
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    /**
     * @Author lilin
     * @Date 2022/9/14 19:35:00
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param tagListDto 标签名和备注实体类
     * @Return
     * @Description 查询标签列表
     */
    @Override
    public Object getPageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());


        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);

        //封装数据返回
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    /**
     * @Author lilin
     * @Date 2022/9/15 11:02:28
     * @Return
     * @Description 查询所有标签
     */
    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getName);
        List<Tag> tagList = list(queryWrapper);
        return CopyBeanUtils.copyBeanList(tagList, TagVo.class);
    }
}


package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.entity.Link;
import com.lilin.vo.LinkVo;

import java.util.List;

/**
 * Created by LiLin on 2022/9/5/10:43:02
 */
public interface LinkService extends IService<Link> {
    List<LinkVo> getLinks();
}

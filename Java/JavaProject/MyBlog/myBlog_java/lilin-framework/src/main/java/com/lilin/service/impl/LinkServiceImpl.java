package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.constants.SystemConstants;
import com.lilin.entity.Link;
import com.lilin.mapper.LinkMapper;
import com.lilin.service.LinkService;
import com.lilin.utils.CopyBeanUtils;
import com.lilin.vo.LinkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiLin on 2022/9/5/10:43:35
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Autowired
    LinkMapper linkMapper;

    /**
     * @Author lilin
     * @Date 2022/9/7 10:37:07
     * @Return
     * @Description 获取所有的赞助商信息
     */
    @Override
    public List<LinkVo> getLinks() {
        //查询审核通过的赞助商
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);

        //封装成 vo
        return CopyBeanUtils.copyBeanList(links, LinkVo.class);
    }
}

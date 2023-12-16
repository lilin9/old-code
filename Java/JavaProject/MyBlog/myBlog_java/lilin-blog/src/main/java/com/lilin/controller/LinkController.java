package com.lilin.controller;

import com.lilin.service.LinkService;
import com.lilin.utils.ResponseResult;
import com.lilin.vo.LinkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LiLin on 2022/9/7/10:35:02
 * 赞助商
 */
@RestController
@RequestMapping("link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    /**
     * @Author lilin
     * @Date 2022/9/7 10:37:07
     * @Return
     * @Description 获取所有的赞助商信息
     */
    @GetMapping("/getAllLink")
    public ResponseResult<Object> getAllLink() {
        List<LinkVo> links = linkService.getLinks();
        return ResponseResult.okResult(links);
    }
}

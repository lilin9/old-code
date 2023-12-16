package com.lilin.controller;

import com.lilin.constants.SystemConstants;
import com.lilin.entity.Comment;
import com.lilin.service.CommentService;
import com.lilin.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiLin on 2022/9/7/13:47:15
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * @Author lilin
     * @Date 2022/9/9 13:57:22
     * @param articleId 评论 id
     * @param pageName 页码
     * @param pageSize 每页条数
     * @Return
     * @Description 查询评论列表
     */
    @GetMapping("/commentList")
    public ResponseResult<Object> commentList(Long articleId, Integer pageName, Integer pageSize) {
        return commentService.getCommentList(SystemConstants.ARTICLE_COMMENT, articleId, pageName, pageSize);
    }

    /**
     * @Author lilin
     * @Date 2022/9/10 10:37:44
     * @param comment 评论实体
     * @Return
     * @Description 添加评论
     */
    @PostMapping
    public ResponseResult<Object> addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    public ResponseResult<Object> linkCommentList(Integer pageName, Integer pageSize) {
        return commentService.getCommentList(SystemConstants.LINK_COMMENT, null, pageName, pageSize);
    }
}

package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.entity.Comment;
import com.lilin.utils.ResponseResult;

/**
 * Created by LiLin on 2022/9/5/10:43:02
 */
public interface CommentService extends IService<Comment> {
    ResponseResult<Object> getCommentList(String commentType, Long articleId, Integer pageName, Integer pageSize);

    ResponseResult<Object> addComment(Comment comment);
}

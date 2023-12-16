package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.constants.SystemConstants;
import com.lilin.entity.Comment;
import com.lilin.enums.AppHttpCodeEnum;
import com.lilin.exception.SystemException;
import com.lilin.mapper.CommentMapper;
import com.lilin.service.CommentService;
import com.lilin.service.UserService;
import com.lilin.utils.CopyBeanUtils;
import com.lilin.utils.ResponseResult;
import com.lilin.vo.CommentVo;
import com.lilin.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by LiLin on 2022/9/5/10:43:35
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserService userService;

    /**
     * @Author lilin
     * @Date 2022/9/9 13:57:22
     * @param commentType
     * @param articleId 评论 id
     * @param pageName 页码
     * @param pageSize 每页条数
     * @Return
     * @Description 查询评论列表
     */
    @Override
    public ResponseResult<Object> getCommentList(String commentType, Long articleId, Integer pageName, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对 articleId 进行判断
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType), Comment::getArticleId, articleId);
        //根评论：判断rootId是否为 -1
        queryWrapper.eq(Comment::getRootId, SystemConstants.COMMENT_IS_ROOT);
        //对 评论类型 进行判断
        queryWrapper.eq(Comment::getType, commentType);

        //分页查询
        Page<Comment> page = new Page<>();
        page(page, queryWrapper);

        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());

        //查询所有根评论对应的子评论集合，并赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    /**
     * @Author lilin
     * @Date 2022/9/10 10:37:44
     * @param comment 评论实体
     * @Return
     * @Description 添加评论
     */
    @Override
    public ResponseResult addComment(Comment comment) {
        //判断评论是否为空
        if (!StringUtils.hasText(comment.getContent()))
            throw new SystemException(AppHttpCodeEnum.COMMENT_CONTENT_IS_NOT_NULL);

        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * @Author lilin
     * @Date 2022/9/10 09:40:26
     * @param list comment 集合
     * @Return
     * @Description 对 commentVo 重新进行封装
     */
    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = CopyBeanUtils.copyBeanList(list, CommentVo.class);
        //遍历 vo 集合
        for (CommentVo commentVo: commentVos) {
            //通过 createBy 查询用户的昵称并且赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //通过 toCommentUserId 查询用户的昵称并且赋值
            //判断 toCommentUserId 是否为 -1，不为 -1 则进行查询
            Long toCommentUserId = commentVo.getToCommentUserId();
            if (toCommentUserId != -1) {
                String toCommentUsername = userService.getById(toCommentUserId).getNickName();
                commentVo.setToCommentUserName(toCommentUsername);
            }
        }

        return commentVos;
    }

    /**
     * @Author lilin
     * @Date 2022/9/10 09:42:23
     * @param id 评论 id
     * @Return
     * @Description 查询父评论下的子评论
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        //对创建时间进行排序
        queryWrapper.orderByAsc(Comment::getCreateTime);

        return toCommentVoList(list(queryWrapper));
    }
}

package org.soft.base.dao.message.impl;

import org.soft.base.dao.message.ReplyDao;
import org.soft.base.mapper.ReplyMapper;
import org.soft.base.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller("replyDao")
public class ReplyDaoImpl implements ReplyDao {
    @Autowired
    private ReplyMapper replyMapper = null;
    @Override
    public boolean replyIssue(Reply reply) {
        boolean b = replyMapper.replyIssueMapper(reply);
        return b;
    }

    @Override
    public List<Reply> repliesByArticleId(Map<String, Object> map) {
        List<Reply> replies = replyMapper.repliesByArticleIdMapper(map);
        return replies;
    }

    @Override
    public boolean replyDeleteById(int replyId) {
        boolean b = replyMapper.replyDeleteByIdMapper(replyId);
        return b;
    }

    @Override
    public boolean replyDeleteByArticleId(int articleId) {
        boolean b = replyMapper.replyDeleteByArticleId(articleId);
        return b;
    }
}

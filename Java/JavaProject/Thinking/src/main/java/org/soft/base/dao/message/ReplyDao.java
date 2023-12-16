package org.soft.base.dao.message;

import org.soft.base.model.Reply;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ReplyDao {

    /**
     * 发布回复信息
     * @param reply
     * @return
     */
    public boolean replyIssue(Reply reply);

    /**
     * 显示指定文章的回复信息
     * @param map
     * @return
     */
    public List<Reply> repliesByArticleId(Map<String ,Object> map);

    /**
     * 删除指定的回复
     * @param replyId
     * @return
     */
    public boolean replyDeleteById(int replyId);

    /**
     * 删除文章相关回复
     * @param articleId
     * @return
     */
    public boolean replyDeleteByArticleId(int articleId);
}

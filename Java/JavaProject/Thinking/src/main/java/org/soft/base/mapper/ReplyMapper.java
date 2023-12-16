package org.soft.base.mapper;

import org.apache.ibatis.annotations.*;
import org.soft.base.model.Reply;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface ReplyMapper {
    /**
     * 发布回复信息
     * @param reply
     * @return
     */
    @Insert("insert into reply (replyContent,replyTime,humanId,articleId) " +
            "value (#{replyContent},now(),#{humanId},#{articleId})")
    public boolean replyIssueMapper(Reply reply);

    /**
     * 显示指定文章的回复信息
     * @param map
     * @return
     */
    @Results({
            @Result(id = true , column = "replyId" , property = "replyId"),
            @Result(column = "humanId" , property = "humanId"),
            @Result(column = "articleId" , property = "articleId"),
            @Result(property = "human" , column = "humanId" ,
            one=@One(select = "org.soft.base.mapper.HumanMapper.humanById"))
    })
    @Select("select * from reply where articleId = #{articleId} order by replyTime desc limit #{begin},#{size}")
    public List<Reply> repliesByArticleIdMapper(Map<String , Object> map);

    /**
     * 删除指定的回复
     * @param replyId
     * @return
     */
    @Delete("delete from reply where replyId = #{replyId}")
    public boolean replyDeleteByIdMapper(int replyId);

    /**
     * 删除文章相关回复
     * @param articleId
     * @return
     */
    @Delete("delete from reply where articleId = #{articleId}")
    public boolean replyDeleteByArticleId(int articleId);
}

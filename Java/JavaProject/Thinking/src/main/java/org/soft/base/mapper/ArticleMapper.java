package org.soft.base.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.soft.base.model.Article;
import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {

    /**
     * 发布文章
     * @param article
     * @return
     */
    @Insert("insert into article(articleId,articleTitle,articleContent,articleIssueTime,humanId,typeId)" +
            " value (#{articleId},#{articleTitle},#{articleContent},now(),#{humanId},#{typeId})")
    public boolean articleIssue(Article article);

    /**
     * 通过文章ID查询文章详细内容
     * @param articleId
     * @return
     */

    @Results({
            @Result(id = true , property = "articleId" , column = "articleId"),
            @Result(property = "humanId" , column = "humanId"),
            @Result(property = "typeId",column = "typeId"),
            @Result(property = "human" , column = "humanId" ,
            one = @One(select = "org.soft.base.mapper.HumanMapper.humanById")),
           @Result(property = "type" ,column = "typeId",
                   one=@One(select = "org.soft.base.mapper.TypeDaoMapper.typeById"))

    })
    @Select("select * from article where articleId = #{articleId}")
    public Article articleById(int articleId);

    /**
     * @param idList idList
     * @Return
     * @Description 根据 id 列表查询文章列表
     * @Author LILIN
     * @Date 2023/8/2 15:50:05
     */
    @Select({
            "<script>" +
                    "SELECT `articleId`,`articleTitle`,`articleContent`,`articleIssueTime`,`humanId`,`typeId` FROM `article` WHERE `articleId` IN" +
                    "<foreach item='item' index='index' collection='idList' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    "ORDER BY FIELD(articleId, " +
                    "<foreach item='item' index='index' collection='idList' open='' separator=',' close=''>" +
                    "#{item}" +
                    "</foreach>" +
                    ");" +
            "</script>"
    })
    public List<Article> articleByIdList(@Param("idList") List<Integer> idList);

    /**
     * 修改文章信息
     * @param article
     * @return
     */
    @Update("update article set articleTitle = #{articleTitle} ,articleContent = #{articleContent}, " +
            "articleIssueTime=now(),typeId = #{typeId} where articleId = #{articleId}")
    public boolean articleUpdate(Article article);

    /**
     * 文章列表，分页
     * @param map begin：起始位置 size：每页文章数量
     * @return
     */
    @Results({
            @Result(id = true , property = "articleId" , column = "articleId"),
            @Result(property = "human" , column = "humanId" ,
                    one = @One(select = "org.soft.base.mapper.HumanMapper.humanById",
                            fetchType = FetchType.EAGER))
    })
    @Select("select * from article order by articleIssueTime desc limit #{begin},#{size}")
    public List<Article> articlesByMap(Map<String,Object> map);

    /**
     * 文章列表，leixing分页
     * @param map begin：起始位置 size：每页文章数量 typeId 类型
     * @return
     */
    @Results({
            @Result(id = true , property = "articleId" , column = "articleId"),
            @Result(property = "human" , column = "humanId" ,
                    one = @One(select = "org.soft.base.mapper.HumanMapper.humanById",
                            fetchType = FetchType.EAGER))
    })
    @Select("select * from article where typeId = #{typeId} " +
            "order by articleIssueTime desc limit #{begin},#{size}")
    public List<Article> articlesByTypeMap(Map<String,Object> map);



    /**
     * 删除文章
     * @param articleId
     * @return
     */
    @Delete("delete from article where articleId = #{articleId}")
    public boolean articleDeleteById(int articleId);

    /**
     * 查看指定用户的文章列表
     * @param map
     * @return
     */
    @Select("select * from article where humanId = #{humanId} " +
            "order by articleIssueTime desc limit #{begin},#{size}")
    public List<Article> articlesByHumanId(Map<String,Object> map);

}

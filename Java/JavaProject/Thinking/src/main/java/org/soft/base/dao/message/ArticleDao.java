package org.soft.base.dao.message;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.soft.base.model.Article;

import java.util.List;
import java.util.Map;

public interface ArticleDao {

    /**
     * 发布文章
     * @param article
     * @return
     */
    public boolean articleIssue(Article article);

    /**
     * 通过文章ID查询文章详细内容
     * @param articleId
     * @return
     */

    public Article articleById(int articleId);

    /**
     * @param idList idList
     * @Return
     * @Description 根据 id 列表查文章数据
     * @Author LILIN
     * @Date 2023/8/2 15:46:53
     */
    public List<Article> articleByIdList(List<Integer> idList);

    /**
     * 修改文章信息
     * @param article
     * @return
     */
    public boolean articleUpdate(Article article);

    /**
     * 文章列表，分页
     * @param map begin：起始位置 size：每页文章数量
     * @return
     */

    public List<Article> articlesByMap(Map<String,Object> map);


    /**
     * 删除文章
     * @param articleId
     * @return
     */
    public boolean articleDeleteById(int articleId);

    /**
     * 查看指定用户的文章列表
     * @param map
     * @return
     */
   public List<Article> articlesByHumanId(Map<String,Object> map);

    /**
     * 文章列表，leixing分页
     * @param map begin：起始位置 size：每页文章数量 typeId 类型
     * @return
     */
    public List<Article> articlesByTypeMap(Map<String,Object> map);

}

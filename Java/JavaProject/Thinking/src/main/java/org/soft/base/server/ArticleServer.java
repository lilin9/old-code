package org.soft.base.server;


import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.soft.base.annotation.Description;
import org.soft.base.dao.constant.ImageUtils;
import org.soft.base.dao.message.ArticleDao;
import org.soft.base.dao.message.ReplyDao;
import org.soft.base.dao.message.SplitDao;
import org.soft.base.dao.constant.SplitConstant;
import org.soft.base.enums.ResponseEnum;
import org.soft.base.mapper.SplitMapper;
import org.soft.base.model.Article;
import org.soft.base.model.Human;
import org.soft.base.model.ResponseResult;
import org.soft.base.utils.CommonUtils;
import org.soft.base.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleServer {

//    private static Logger log = Logger.getLogger(ArticleServer.class.getClass());

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private CommonUtils commonUtils;

    //读取配置文件里存的 redis key
    @Value("${my.redis.zset.articleKey}")
    private String articleKey;
    //读取配置文件里面的 redis 的 zset 的前缀
    @Value("${my.redis.zset.articleMember}")
    private String articlePrefix;
    //读取文章榜单刷新时间
    @Value("${my.refreshHour}")
    private Long refreshHour;
    @Value("${my.refreshMinute}")
    private Long refreshMinute;
    //保存到本地的文章排行榜文件
    @Value("${my.fileName}")
    private String fileName;
    @Value("${my.filePath}")
    private String filePath;


    @Autowired
    @Qualifier("articleDao")
    private ArticleDao articleDao;
    @Autowired
    private SplitMapper splitMapper;
    @Autowired
    @Qualifier("splitDao")
    private SplitDao splitDao;

    @Autowired
    @Qualifier("replyDao")
    private ReplyDao replyDao;

    @Autowired
    @Qualifier("imageUtils")
    private ImageUtils imageUtils;
    private String result = null;


    /**
     * 该功能迁移到IssueServer类中
     *
     * @param article
     * @param session
     * @param model
     * @return
     */

    @Deprecated
    @RequestMapping("/issue")
    @Description(message = "该功能迁移到IssueServer类中")
    public String articleIssue(Article article, HttpSession session, Model model) {
        Human human = (Human) session.getAttribute("human");
        int humanId = human.getHumanId();
        System.out.println("humanId = " + humanId);
        article.setHumanId(humanId);
        boolean b = articleDao.articleIssue(article);
        if (b) {
//            model.addAttribute("article" , article);
            result = "redirect:/article/articleListbyId/1";
        } else {
            model.addAttribute("msg", "文章发布失败");
            result = "article/error";
        }
        return result;
    }

    /**
     * 查询指定页的内容，显示在指定的页面
     *
     * @param currentPage
     * @return
     */
    @PostMapping("/articleList/{currentPage}")
    @Description(message = "查询指定页的内容，显示在指定的页面")
    public ResponseResult<Map<String, Object>> articleList(@PathVariable("currentPage") int currentPage) {
        Map<String, Object> tableMap = new HashMap<>();
        tableMap.put("tableName", SplitConstant.TABLENAME);
        // 总行数
        int rows = splitMapper.getArticleRows(tableMap);
        // 总页数
        int allPage = splitDao.allPage(rows, SplitConstant.SIZE);
        int begin = 0;
        if (currentPage > 0) {
            // select * from article limit 0,10
            // 当前页在数据库中开始的位置
            begin = splitDao.rowBegin(currentPage, SplitConstant.SIZE);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("begin", begin);
        map.put("size", SplitConstant.SIZE);
        // 每页显示的内容
        List<Article> articles = articleDao.articlesByMap(map);

        //将 articles 和 allPage 封装成一个 Map
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("allPage", allPage);
        resultMap.put("articles", articles);

        //将 resultMap 封装进响应体里面,并且返回
        log.info("文章数据列表 {}", resultMap);
        return new ResponseResult<>(ResponseEnum.SUCCESS, resultMap);
    }

    @PostMapping("/articleTypeList/{currentPage}/{typeId}")
    @Description(message = "获取文章类型列表")
    public ResponseResult<Object> articleTypeList(@PathVariable("currentPage") int currentPage,
                                                  @PathVariable("typeId") int typeId) {
        Map<String, Object> tableMap = new HashMap<>();
        tableMap.put("tableName", SplitConstant.TABLENAME);
        tableMap.put("typeId", typeId);
        // 总行数
        int rows = splitMapper.getArticleRows(tableMap);
        int allPage = splitDao.allPage(rows, SplitConstant.SIZE);
        int begin = 0;
        if (currentPage > 0) {
            begin = splitDao.rowBegin(currentPage, SplitConstant.SIZE);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("begin", begin);
        map.put("size", SplitConstant.SIZE);
        map.put("typeId", typeId);
        List<Article> articles = articleDao.articlesByTypeMap(map);

        //要返回的数据
        Map<String, Object> result = new HashMap<>();
        result.put("allPage", allPage);
        result.put("articles", articles);
        result.put("typeId", typeId);
        return new ResponseResult<>(ResponseEnum.SUCCESS, result);
    }

    @PostMapping("/articleById/{articleId}")
    @Description(message = "根据 id 获取文章数据")
    public ResponseResult<Object> articleById(@PathVariable("articleId") int articleId) {
        Article article = articleDao.articleById(articleId);
//        model.addAttribute("article", article);
//        result = "article/articlebyid";

        return new ResponseResult<>(ResponseEnum.SUCCESS, article);
    }

    @RequestMapping("/update")
    @Description(message = "根据 id 修改文章数据")
    public ResponseResult<Object> articleUpdateById(@RequestBody Article article) {
        boolean b = articleDao.articleUpdate(article);
        int articleId = article.getArticleId();
        return new ResponseResult<>(ResponseEnum.SUCCESS, articleId);
    }

    //no page using
    @RequestMapping("/articleListbyId/{currentPage}")
    @Description(message = "根据 id 查询文章列表数据")
    public ResponseResult<Object> articleListById(@PathVariable("currentPage") int currentPage,
                                                  HttpSession session) {
        Map<String, Object> tableMap = new HashMap<>();
        tableMap.put("tableName", SplitConstant.TABLENAME);
        Human human = (Human) session.getAttribute("human");
        int humanId = human.getHumanId();
        tableMap.put("humanId", humanId);
        // 总行数
        int rows = splitMapper.getArticleRows(tableMap);

        int allPage = splitDao.allPage(rows, SplitConstant.ByHumanIdSize);

        int begin = 0;
        if (currentPage > 0) {
            begin = splitDao.rowBegin(currentPage, SplitConstant.ByHumanIdSize);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("humanId", humanId);
        map.put("begin", begin);
        map.put("size", SplitConstant.ByHumanIdSize);
        List<Article> articles = articleDao.articlesByHumanId(map);

        //定义一个返回的 map
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("allPage", allPage);
        resultMap.put("articles", articles);
//        return "view/index";
        //将 resultMap 返回
        return new ResponseResult<>(ResponseEnum.SUCCESS, resultMap);
    }

    @PostMapping("/del/{articleId}")
    @Description(message = "根据 id 删除文章数据")
    public ResponseResult<Object> articleDelById(@PathVariable("articleId") int articleId) {
        Article article = articleDao.articleById(articleId);
        String content = article.getArticleContent();
        List<String> imageList = imageUtils.getImageSrc(content);
        imageUtils.deleteFile(imageList);
        boolean b = articleDao.articleDeleteById(articleId);
        result = "view/login";

        ResponseResult<Object> responseResult = new ResponseResult<>(ResponseEnum.FAILED);
        if (b) {
            Map<String, Object> map = new HashMap<>();
            map.put("tableName", "reply");
            map.put("articleId", articleId);
            int row = splitMapper.getArticleRows(map);
            if (row > 0) {
                b = replyDao.replyDeleteByArticleId(articleId);
            }
            result = "redirect:/article/articleListbyId/1";
            //删除成功，返回删除成功信息
            responseResult = new ResponseResult<>(ResponseEnum.SUCCESS);
        }
        return responseResult;
    }

    /**
     * @Return
     * @Description 根据文章的点击量查询排名前十的文章内容。
     * 在每天指定时间查询 redis 中的排行榜数据，然后将其写入本地文件，
     * 在之后的时间只读取本地文件数据，在本地文件数据存在的情况下
     * @Author LILIN
     * @Date 2023/8/1 16:12:09
     */
    @PostMapping("/ranking")
    @Description(message = "根据文章的点击量查询排名前十的文章内容。" +
            "在每天指定时间查询 redis 中的排行榜数据，然后将其写入本地文件，" +
            "在之后的时间只读取本地文件数据，在本地文件数据存在的情况下")
    public ResponseResult<Object> articleListForRankingBetter() {
        //打开本地文件
        File file = new File(filePath + "/" + fileName + ".txt");

        List<Integer> idList;
        //在每天早上六点整刷新文章排行榜，如果本地文件不存在也要刷新
        if (commonUtils.isSpecificTime(refreshHour, refreshMinute) || !file.exists()) {
            //查询 redis 里面的 top10 文章数据
            Set<Object> articleIdList = redisUtils.reverseRange(articleKey, 1, 10);        //去掉 set 数据的前缀，取得文章 id 列表
            idList = commonUtils.removePrefix(articleIdList, articlePrefix);
            //将 idList 写入本地文件
            try {
                commonUtils.fileWrite(fileName, filePath, commonUtils.convertListToString(idList));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            //从本地文件读取出 idList 字符串，然后转换成 List<String>
            List<String> list;
            try {
                list = Arrays.stream(commonUtils.fileRead(fileName, filePath).split("\r\n")).toList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //将 List<String> 转成 List<Integer> 后赋给 idList
            idList = list.stream().map(Integer::parseInt).collect(Collectors.toList());
        }

        //查询文章数据，然后封装返回
        return new ResponseResult<>(ResponseEnum.SUCCESS,
                articleDao.articleByIdList(idList));
    }

    public ResponseResult<Object> articleListForRanking() {
        //查询 redis 里面的 top10 文章数据
        Set<Object> articleIdList = redisUtils.reverseRange(articleKey, 1, 10);
        //去掉 set 数据的前缀，取得文章 id 列表
        List<Integer> idList = commonUtils.removePrefix(articleIdList, articlePrefix);

        //查询文章数据，然后封装返回
        return new ResponseResult<>(ResponseEnum.SUCCESS,
                articleDao.articleByIdList(idList));
    }
}

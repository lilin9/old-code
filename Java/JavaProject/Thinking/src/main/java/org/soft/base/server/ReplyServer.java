package org.soft.base.server;

import jakarta.servlet.http.HttpSession;
import org.soft.base.annotation.Description;
import org.soft.base.dao.message.SplitDao;
import org.soft.base.dao.constant.SplitConstant;
import org.soft.base.dao.message.ReplyDao;
import org.soft.base.enums.ResponseEnum;
import org.soft.base.mapper.SplitMapper;
import org.soft.base.model.Human;
import org.soft.base.model.Reply;
import org.soft.base.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/reply")
@RestController
public class ReplyServer {

    @Autowired
    @Qualifier("replyDao")
    private ReplyDao replyDao;

    @Autowired
    @Qualifier("splitDao")
    private SplitDao splitDao = null;

    @Autowired
    private SplitMapper splitMapper;

    @PostMapping("/issue")
    @Description(message = "发布评论信息")
    public ResponseResult<Object> repliesIssueServer(HttpSession session,
                                                     @RequestBody Reply reply){
        Human human = (Human)session.getAttribute("human");
        int humanId = human.getHumanId();
        reply.setHumanId(humanId);
        // 发布回复信息
        boolean b = replyDao.replyIssue(reply);
        return new ResponseResult<>(ResponseEnum.SUCCESS);
    }

    @RequestMapping("/split/{articleId}/{currentPage}")
    @Description(message = "根据文章id查询文章评论")
    public ResponseResult<Object> repliesShowServer(@PathVariable("articleId") int articleId
            ,@PathVariable("currentPage") int currentPage){
        Map<String,Object> map = new HashMap<>();
        map.put("articleId" , articleId);
        int allPage = 0;
        int begin = 0;
        if(currentPage > 0){
            Map<String,Object> tableMap = new HashMap<>();
            tableMap.put("tableName" , "reply");
            tableMap.put("articleId" , articleId);
            int rows = splitMapper.getArticleRows(tableMap);
            allPage = splitDao.allPage(rows , SplitConstant.REPLYSIZE);
            begin = splitDao.rowBegin(currentPage , SplitConstant.REPLYSIZE);
        }
        map.put("begin" , begin);
        map.put("size" , SplitConstant.REPLYSIZE);
        List<Reply> replies = replyDao.repliesByArticleId(map);
        Map<String,Object> model = new HashMap<>();
        model.put("replies" , replies);
        model.put("allPage" ,allPage);
        return new ResponseResult<>(ResponseEnum.SUCCESS, model);
    }

    @RequestMapping("/del/{replyId}")
    @Description(message = "根据评论 id 删除评论")
    public boolean replyDelById( @PathVariable("replyId") int replyId){
        boolean b = replyDao.replyDeleteById(replyId);
        return b;
    }

    
}

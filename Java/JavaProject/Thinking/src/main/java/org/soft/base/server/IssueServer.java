package org.soft.base.server;

import jakarta.servlet.http.HttpSession;
import org.soft.base.annotation.Description;
import org.soft.base.dao.constant.ImageUtils;
import org.soft.base.dao.constant.SplitConstant;
import org.soft.base.dao.message.ArticleDao;
import org.soft.base.enums.ResponseEnum;
import org.soft.base.model.*;
import org.soft.base.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/issue")
public class IssueServer {
    private List<String> listOld = new ArrayList<>();
    private String tag = SplitConstant.tag;
    private String path = SplitConstant.path;

    @Autowired
    @Qualifier("articleDao")
    private ArticleDao articleDao;

    @Autowired
    @Qualifier("imageUtils")
    private ImageUtils imageUtils;


    @RequestMapping("/article-image-upload")
    @Description(message = "")
    public FileVO articleIssueController(@RequestParam("file") MultipartFile file) {

        FileVO fileVO = new FileVO();
        UUID uuid = UUID.randomUUID();
        String fileName = System.currentTimeMillis() + uuid.toString() + file.getOriginalFilename();

        File filenew = new File(path, fileName);
        try {
            file.transferTo(filenew);
            fileVO.setErrno(0);
            String[] values = {tag + fileName};
            listOld.add(fileName);
            fileVO.setData(values);
//            System.out.println( JSON.toJSONString(fileVO));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileVO;
    }


    @RequestMapping("/updateById/{articleId}")
    @Description(message = "根据文章id查询文章数据")
    public ResponseResult<Object> getArticleByIdToUpdate(@PathVariable("articleId") int articleId) {
        Article article = articleDao.articleById(articleId);
        return new ResponseResult<>(ResponseEnum.SUCCESS, article);

    }

    @RequestMapping("/articleIssue")
    @Description(message = "发布文章")
    public ResponseResult<Object> articleIssue(Article article, HttpSession session) {
        Human human = (Human) session.getAttribute("human");
        int humanId = human.getHumanId();
        System.out.println("humanId = " + humanId);
        article.setHumanId(humanId);
        boolean b = articleDao.articleIssue(article);
        if (b) {
            String result = article.getResult();
            if (result != null) {
                String[] results = result.split(",");
                List<String> listOne = new ArrayList<>(Arrays.asList(results));
                for (String v1 : listOne) {
                    v1 = v1.substring(tag.length(), v1.length());
                    listOld.remove(v1);
                }
            }
            if (listOld.size() > 0) {
//                for (String fileName : listOld) {
//                    File file = new File(path + fileName);
//                    file.delete();
//                }

                imageUtils.deleteFile(listOld);
            }

        }
        return b ?
                new ResponseResult<>(ResponseEnum.SUCCESS) :
                new ResponseResult<>(ResponseEnum.FAILED);
    }
}

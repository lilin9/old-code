package org.soft.base;

import org.junit.jupiter.api.Test;
import org.soft.base.dao.message.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LILIN on 2023/8/2/16:08:47
 */
@SpringBootTest
public class ArticleDaoTests {
    @Autowired
    private ArticleDao articleDao;

    @Test
    public void articleByIdListTest() {
        List<Integer> ids = new ArrayList<>();
        ids.add(2);
        ids.add(1);
        ids.add(4);
        ids.add(5);

        articleDao.articleByIdList(ids).forEach(System.out::println);
    }
}

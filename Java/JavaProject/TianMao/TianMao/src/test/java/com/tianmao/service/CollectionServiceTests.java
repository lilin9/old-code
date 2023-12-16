package com.tianmao.service;

import com.tianmao.vo.CollectionVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by LiLin on 2022/05/17/14:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CollectionServiceTests {
    @Autowired
    CollectionService collectionService;

    @Test
    public void insertToCollection() {
        collectionService.insertToCollection(8, 10000003);
    }

    @Test
    public void selectCollectionVoByUid() {
        List<CollectionVo> list = collectionService.selectCollectionVoByUid(8);
        list.forEach(System.out :: println);
    }

    @Test
    public void deleteCollection() {
        collectionService.deleteCollection(10000003, 7);
    }
}

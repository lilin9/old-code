package com.tianmao.mapper;

import com.tianmao.pojo.Collection;
import com.tianmao.vo.CollectionVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by LiLin on 2022/05/17/12:05
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CollectionMapperTests {
    @Autowired
    CollectionMapper collectionMapper;

    @Test
    public void selectCollectionByPid() {
        System.out.println(collectionMapper.selectCollectionByPid(10000002));
    }

    @Test
    public void insertToCollection() {
        collectionMapper.insertToCollection(
                new Collection(null, 8,10000002));
    }

    @Test
    public void selectCollectionVo() {
        List<CollectionVo> list = collectionMapper.selectCollectionVoByUid(8);
        list.forEach(System.out :: println);
    }

    @Test
    public void deleteCollectionByCoid() {
        System.out.println(collectionMapper.deleteCollectionByPid(10000003));
    }

    @Test
    public void selectCollectionByCoid() {
        System.out.println(collectionMapper.selectCollectionByCoid(3));
    }
}

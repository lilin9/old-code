package com.lilin;

import com.lilin.mapper.CollectRecordMapper;
import com.lilin.vo.CollectDetailVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiLin on 2022/10/8/14:50:43
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PartTimeAdminTest {
    @Autowired
    private CollectRecordMapper collectRecordMapper;

    @Test
    public void test() {
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        userIds.add(5L);

        List<CollectDetailVo> list = collectRecordMapper.getAllCollectsByUserIds(userIds);
        list.forEach(System.out::println);
    }
}

package com.MrLi.test;

import com.MrLi.mapper.SelectMapper;
import com.MrLi.pojo.User;
import com.MrLi.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by MrLi on 2022/03/03/16:07
 */
public class SelectMapperTest {
    /*
        mybatis的各种查询功能：
            1、若查询出的数据只有一条，可以通过实体类对象或者列表接收
            2、若查询出的数据有多条，可以通过列表接收，但一定不可以通过实体类对象接受
     */
    @Test
    public void getUserById() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        User user = mapper.getUserById(3);
        System.out.println(user);
    }

    @Test
    public void getAllUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<User> users = mapper.getAllUser();
        users.forEach(System.out::println);
    }

    @Test
    public void getUserCount() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Integer count = mapper.getUserCount();
        System.out.println(count);
    }

    @Test
    public void getUserByIdToMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> user = mapper.getUserByIdToMap(1);
        System.out.println(user);
    }

    @Test
    public void getAllUserIdToMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> users = mapper.getAllUserToMap();
        System.out.println(users);
    }
}

package com.MrLi.test;

import com.MrLi.mapper.ParameterMapper;
import com.MrLi.pojo.User;
import com.MrLi.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MrLi on 2022/03/02/20:41
 */
public class ParameterMapperTest {
    @Test
    public void queryUsersTest() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> users = parameterMapper.queryUsers();

        users.forEach(System.out :: println);
    }

    @Test
    public void queryUserByUsername() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User user = parameterMapper.queryUserByUsername("lily");

        System.out.println(user);
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User user = parameterMapper.queryUserByUsernameAndPassword("tom", "123abc");

        System.out.println(user);
    }

    @Test
    public void queryUserByMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("username", "tom");
        map.put("password", "123abc");

        User user = parameterMapper.queryUserByMap(map);

        System.out.println(user);
    }

    @Test
    public void insertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        parameterMapper.insertUser(new User(null, "tom", "123abc", 15, "boy", "tom@qq.com"));
    }

    @Test
    public void queryUserByParam() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User user = parameterMapper.queryUserByUsernameAndPassword("jery", "123abc");

        System.out.println(user);
    }
}

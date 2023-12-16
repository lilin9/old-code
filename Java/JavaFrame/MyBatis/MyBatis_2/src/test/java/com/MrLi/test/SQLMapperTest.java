package com.MrLi.test;

import com.MrLi.mapper.SQLMapper;
import com.MrLi.pojo.User;
import com.MrLi.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by MrLi on 2022/03/03/17:11
 */
public class SQLMapperTest {
    @Test
    public void getUserByLike() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        System.out.println(mapper.getUserByLike("s"));
    }

    @Test
    public void deleteMore() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        System.out.println(mapper.deleteMore("1,2,3"));
    }

    @Test
    public void getUserByTableName() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> users = mapper.getUserByTableName("t_user");
        users.forEach(System.out::println);
    }

    @Test
    public void insertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        User user = new User(null,"amy","123abc",15,"girl","amy@qq.com");
        mapper.insertUser(user);
        System.out.println(user.getId());
    }
}

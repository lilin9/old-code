package com.MrLi.test;

import com.MrLi.mapper.DynamicSQLMapper;
import com.MrLi.pojo.Emp;
import com.MrLi.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrLi on 2022/03/05/16:23
 */
public class DynamicSQLMapperTest {
    @Test
    public void getEmpByCondition() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emp = mapper.getEmpByCondition(new Emp(null, "", 18, "boy", "tom@qq.com"));
        emp.forEach(System.out::println);
    }

    @Test
    public void getEmpByChoose() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emp = mapper.getEmpByChoose(new Emp(null, "", null, "", ""));
        emp.forEach(System.out::println);
    }

    @Test
    public void deleteMoreByArray() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        mapper.deleteMoreByArray(new Integer[]{1, 2, 3});
    }

    @Test
    public void insertMoreByList() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);

        Emp emp1 = new Emp(null, "boy1", 13, "boy", "boy1.@qq.com");
        Emp emp2 = new Emp(null, "boy2", 13, "boy", "boy2.@qq.com");
        Emp emp3 = new Emp(null, "boy3", 13, "boy", "boy3.@qq.com");
        Emp emp4 = new Emp(null, "boy4", 13, "boy", "boy4.@qq.com");

        ArrayList<Emp> arrayList = new ArrayList<>();
        arrayList.add(emp1);
        arrayList.add(emp2);
        arrayList.add(emp3);
        arrayList.add(emp4);
        mapper.insertMoreByList(arrayList);
    }
}

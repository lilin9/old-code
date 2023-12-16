package com.MrLi.test;

import com.MrLi.mapper.EmpMapper;
import com.MrLi.pojo.Emp;
import com.MrLi.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by MrLi on 2022/03/04/14:23
 */
public class ResultTest {
    @Test
    public void getAllEmp() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> empList = empMapper.getAllEmp();
        empList.forEach(System.out::println);
    }

    @Test
    public void getEmpAndDept() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = empMapper.getEmpAndDept(1);
        System.out.println(emp);
    }

    @Test
    public void getEmpAndDeptByStep() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = empMapper.getEmpAndDeptByStepOne(3);
        System.out.println(emp);
    }
}

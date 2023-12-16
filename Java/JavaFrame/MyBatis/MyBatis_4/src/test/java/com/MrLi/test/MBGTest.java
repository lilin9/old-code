package com.MrLi.test;

import com.MrLi.mapper.EmpMapper;
import com.MrLi.pojo.Emp;
import com.MrLi.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created by MrLi on 2022/03/07/15:37
 */
public class MBGTest {
    @Test
    public void test() {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sessionFactory.openSession();
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //查询所有数据
            //List<Emp> list = mapper.selectByExample(null);
            //list.forEach(System.out::println);

            //根据条件查询
            EmpExample example = new EmpExample();
            example.createCriteria().andEmpNameEqualTo("tom");
            List<Emp> list = mapper.selectByExample(example);
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

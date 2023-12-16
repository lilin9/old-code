package com.MrLi.test;

import com.MrLi.mapper.EmpMapper;
import com.MrLi.pojo.Emp;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by MrLi on 2022/03/07/16:15
 */
public class PageHelperTest {
    /*
        实现分页功能需要的元素有：limit、index、pageSize、pageNum
            index：当前页的起始索引
            pageSize：每页显示的条数
            pageNum：当前页的页码
            index = (pageNum - 1) * pageSize

        使用MyBatis的分页插件实现分页功能：
            1、需要在查询功能之前开启分页
                PageHelper.startPage(int pageNum, int pageSize);
            2、在查询功能之后获取分页相关信息
                PageInfo<Emp> page = new pageInfo<>(list, 5);
                list --> 表示分页数据
                5    --> 表示当前导航分页的数量
     */
    @Test
    public void test() {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sessionFactory.openSession();
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

            PageHelper.startPage(1, 4);

            List<Emp> list = mapper.selectByExample(null);
            list.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

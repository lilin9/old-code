package com.MrLi.mapper;

import com.MrLi.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrLi on 2022/03/05/16:08
 */
public interface DynamicSQLMapper {
    //多条件查询
    List<Emp> getEmpByCondition(Emp emp);

    //测试choose、when、otherwise
    List<Emp> getEmpByChoose(Emp emp);

    //通过数组实现批量删除
    int deleteMoreByArray(@Param("eids") Integer[] eids);

    //通过list集合实现批量添加功能
    ArrayList<Emp> insertMoreByList(@Param("emps") ArrayList<Emp> emps);
}

package com.MrLi.mapper;

import com.MrLi.pojo.Dept;
import org.apache.ibatis.annotations.Param;

/**
 * Created by MrLi on 2022/03/04/14:11
 */
public interface DeptMapper {
    //通过分布查询查询员工以及员工所对应的部门信息
    //分布查询第二步：通过did查询员工所对应的部门
    Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);
}

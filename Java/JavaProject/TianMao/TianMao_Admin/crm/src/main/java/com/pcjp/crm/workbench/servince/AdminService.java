package com.pcjp.crm.workbench.servince;

import com.pcjp.crm.workbench.domain.Admin;

import java.util.List;
import java.util.Map;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2022-06-04 12:15:02
 */
public interface AdminService {


    /**
     * 查询用户的密码是否正确
     */
    Admin selectUserByLoginActAndpwd(Map<String,Object> map);


    /**
     * 查询所有管理员
     */
    List<Admin> selectAllAdmin();


}

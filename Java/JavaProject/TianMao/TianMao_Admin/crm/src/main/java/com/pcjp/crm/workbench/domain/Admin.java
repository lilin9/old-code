package com.pcjp.crm.workbench.domain;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2022-06-04 12:15:01
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 366450374288064937L;
    /**
     * 管理员id
     */
    private Integer adminid;
    /**
     * 管理员账号
     */
    private String adminuser;
    /**
     * 管理员密码
     */
    private String adminpass;


    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getAdminuser() {
        return adminuser;
    }

    public void setAdminuser(String adminuser) {
        this.adminuser = adminuser;
    }

    public String getAdminpass() {
        return adminpass;
    }

    public void setAdminpass(String adminpass) {
        this.adminpass = adminpass;
    }

}


package com.pcjp.crm.workbench.servince.impl;

import com.pcjp.crm.workbench.domain.Admin;
import com.pcjp.crm.workbench.mapper.AdminDao;
import com.pcjp.crm.workbench.servince.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2022-06-04 12:15:03
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin selectUserByLoginActAndpwd(Map<String, Object> map) {
        return adminDao.selectUserByLoginActAndpwd(map);
    }

    @Override
    public List<Admin> selectAllAdmin() {
        return adminDao.selectAllAdmin();
    }
}

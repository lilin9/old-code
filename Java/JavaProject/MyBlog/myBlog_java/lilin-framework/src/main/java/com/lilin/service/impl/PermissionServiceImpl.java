package com.lilin.service.impl;

import com.lilin.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiLin on 2022/9/15/15:45:33
 */
@Service("ps")
public class PermissionServiceImpl {
    /**
     * @Author lilin
     * @Date 2022/9/15 15:56:51
     * @param permission 用户的权限
     * @Return
     * @Description 判断用户的权限大小
     */
    public Boolean hasPermission(String permission) {
        //判断用户是否具有超级管理员权限
        if (SecurityUtils.isAdmin())
            return true;

        //否则，获取当前用户具有的权限列表，判断是否存在 permission
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}

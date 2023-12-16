package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2022-09-13 16:36:33
 */
public interface RoleService extends IService<Role> {

    List<String> selectRolesByUserId(Long userId);
}


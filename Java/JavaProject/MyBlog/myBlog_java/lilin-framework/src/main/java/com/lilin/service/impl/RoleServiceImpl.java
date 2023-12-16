package com.lilin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.constants.SystemConstants;
import com.lilin.entity.Role;
import com.lilin.mapper.RoleMapper;
import com.lilin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2022-09-13 16:36:33
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * @Author lilin
     * @Date 2022/9/13 16:53:38
     * @param userId 用户id
     * @Return
     * @Description 获取用户对应的角色信息
     */
    @Override
    public List<String> selectRolesByUserId(Long userId) {
        //判断是否是管理员，如果是返回集合中只需要有 admin
        if (Objects.equals(SystemConstants.IS_USER_ADMIN, userId)) {
            ArrayList<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //如果不是，就查询用户拥有的管理员权限
        return getBaseMapper().selectRoleKeyByUserId(userId);
    }
}


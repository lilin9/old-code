package com.lilin.handler;

import com.lilin.constant.ResponseCodeEnum;
import com.lilin.entity.User;
import com.lilin.exception.CustomizeException;
import com.lilin.service.UserService;
import com.lilin.utils.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by LiLin on 2022/9/22/10:12:42
 * shiro 自定义权限认证方式
 */
@Component
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /*
    授权
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录用户信息
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //查询当前登录用户信息
        User user = userService.selectUserForUsername(userName);

        //如果查不到用户信息
        if (Objects.isNull(user))
            throw new CustomizeException(ResponseCodeEnum.USER_NOT_EXIST);

        //创建对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //封装当前登录用户的角色信息
        info.addRole(user.getType());

        //将信息返回
        return info;
    }

    /*
    认证,失败返回 null
    authenticationToken：主体传过来的认证信息；
    客户端传来的 username 和 password 会自动封装到 token
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取 用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());

        //2.从数据库中查询用户信息
        User user = userService.selectUserForUsername(username);

        //如果查不到用户信息
        if (Objects.isNull(user))
            throw new CustomizeException(ResponseCodeEnum.USER_NOT_EXIST);
        //比较密码
        String salt = user.getSalt();
        if (!SecurityUtils.LockPassword(password, salt).equals(user.getPassword()))
            throw new CustomizeException(ResponseCodeEnum.PASSWORD_FAIL);

        //返回 SimpleAuthenticationInfo 对象
        return new SimpleAuthenticationInfo(user, password, salt);
    }
}

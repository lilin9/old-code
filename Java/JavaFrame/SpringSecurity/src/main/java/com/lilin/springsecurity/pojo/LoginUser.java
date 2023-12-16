package com.lilin.springsecurity.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LiLin on 2022/7/7/14:36:02
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
    private User user;
    private List<String> permission;    //用户权限

    public LoginUser(User user, List<String> permission) {
        this.user = user;
        this.permission = permission;
    }

    @JSONField(serialize = false)   //禁止将 authoritiesList 序列化
    private List<SimpleGrantedAuthority> authoritiesList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //将 permission 中包含的 string 类型的权限信息封装成 SimpleGrantedAuthority 对象]
        if (authoritiesList.size() == 0)
            authoritiesList = permission.
                    stream().
                    map(SimpleGrantedAuthority :: new).
                    collect(Collectors.toList());
        return authoritiesList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

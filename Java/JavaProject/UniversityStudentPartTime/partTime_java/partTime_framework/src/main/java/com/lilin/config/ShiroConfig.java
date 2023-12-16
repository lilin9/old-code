package com.lilin.config;

import com.lilin.constant.GlobalConstant;
import com.lilin.handler.ShiroRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * Created by LiLin on 2022/9/22/14:36:59
 * shiro 配置文件
 */
@Configuration
public class ShiroConfig {
    /*
    自定义过滤器 Realm

    认证过滤器：
        anon：无需认证即可访问，游客身份。
        authc：必须认证（登录）才能访问。
        authcBasic：需要通过 httpBasic 认证。
        user：不一定已通过认证，只要是曾经被 Shiro 记住过登录状态的用户就可以正常发起请求，比如 rememberMe。

        授权过滤器:
        perms：必须拥有对某个资源的访问权限（授权）才能访问。
        role：必须拥有某个角色权限才能访问。
        port：请求的端口必须为指定值才可以访问。
        rest：请求必须是 RESTful，method 为 post、get、delete、put。
        ssl：必须是安全的 URL 请求，协议为 HTTPS。
     */
    @Bean
    public ShiroFilterFactoryBean filterFactoryBean() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager());

        //设置过滤条件
        HashMap<String, String> map = new HashMap<>();
        map.put("/user/register", GlobalConstant.ANON);
        map.put("/user/login", GlobalConstant.ANON);
        map.put("/positions/**", GlobalConstant.ANON);
        map.put("/positions/publishing", GlobalConstant.AUTHC);
        map.put("/city/**", GlobalConstant.ANON);
        map.put("/admin/user/login", GlobalConstant.ANON);

        factoryBean.setFilterChainDefinitionMap(map);
        //设置登录页面
        factoryBean.setLoginUrl("user/login");
        return factoryBean;
    }
    //开启对shiro注解的支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager());
        return advisor;
    }

    //开启aop注解支持
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /* 注册 Realm */
    @Bean
    public DefaultWebSecurityManager manager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(shiroRealm());
        return manager;
    }

    @Bean
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }
}
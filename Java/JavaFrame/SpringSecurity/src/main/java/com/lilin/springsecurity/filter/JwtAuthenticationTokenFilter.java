package com.lilin.springsecurity.filter;

import com.lilin.springsecurity.pojo.LoginUser;
import com.lilin.springsecurity.utils.JWTUtil;
import com.lilin.springsecurity.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by LiLin on 2022/7/8/14:30:45
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        //解析token
        String uid = JWTUtil.parseJWT(token).getSubject();
        if (!StringUtils.hasText(uid))
            throw new RuntimeException("非法token");

        //从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("login:" + uid);
        if (Objects.isNull(loginUser))
            throw new RuntimeException("用户未登录");

        //存入SecurityContextHandler
        //TODO 获取权限信息封装到 authenticationToken 中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }
}

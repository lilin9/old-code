package com.lilin.filter;

import com.alibaba.fastjson.JSON;
import com.lilin.constants.SystemConstants;
import com.lilin.entity.LoginUser;
import com.lilin.enums.AppHttpCodeEnum;
import com.lilin.utils.JwtUtil;
import com.lilin.utils.RedisCache;
import com.lilin.utils.ResponseResult;
import com.lilin.utils.WebUtils;
import io.jsonwebtoken.Claims;
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
 * Created by LiLin on 2022/9/7/16:33:18
 * security 认证过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的 token
        String token = request.getHeader("token");
        //如果没有 token，说明此请求不需要登录
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }

        //解析获取 userId
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            //如果 token 有异常，即：token 超时或 token 非法
            //响应告诉前端需要重新登录
            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(responseResult));
            return;
        }

        String userId = claims.getSubject();
        //从 redis 中获取用户信息
        String userString = redisCache.getCacheObject(SystemConstants.TOKEN_PRE + userId);
        LoginUser loginUser = (LoginUser) JSON.parseArray(userString, LoginUser.class);
        if (Objects.isNull(loginUser)) {
            //如果为空，说明登陆过期，需要重新登录
            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(responseResult));
            return;
        }
        //将得到的用户信息存入 SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }
}

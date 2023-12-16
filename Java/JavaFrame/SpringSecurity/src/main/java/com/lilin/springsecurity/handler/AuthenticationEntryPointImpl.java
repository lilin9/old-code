package com.lilin.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.lilin.springsecurity.utils.ResponseResult;
import com.lilin.springsecurity.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LiLin on 2022/7/10/12:26:10
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        ResponseResult<Void> result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(),
                "认证失败，请重新登录");
        //处理异常
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}

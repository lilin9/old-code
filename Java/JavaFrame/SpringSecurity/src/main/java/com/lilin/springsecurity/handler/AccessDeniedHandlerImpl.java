package com.lilin.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.lilin.springsecurity.utils.ResponseResult;
import com.lilin.springsecurity.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LiLin on 2022/7/10/13:05:09
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult<Void> result = new ResponseResult<>(HttpStatus.FORBIDDEN.value(),
                "你的权限不足");
        //处理异常
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}

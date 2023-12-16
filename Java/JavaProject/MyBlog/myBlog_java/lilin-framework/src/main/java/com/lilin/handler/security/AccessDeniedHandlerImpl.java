package com.lilin.handler.security;

import com.alibaba.fastjson.JSON;
import com.lilin.enums.AppHttpCodeEnum;
import com.lilin.utils.ResponseResult;
import com.lilin.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LiLin on 2022/9/7/19:47:55
 * 授权失败处理器
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //打印错误信息
        accessDeniedException.printStackTrace();
        //将错误信息响应给前端
        WebUtils.renderString(response,
                JSON.toJSONString(ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH)));
    }
}

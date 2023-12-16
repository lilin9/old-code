package com.lilin.springsecurity.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LiLin on 2022/7/7/10:57:05
 */
public class WebUtils {
    //将字符串渲染到客户端
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        } return null;
    }
}

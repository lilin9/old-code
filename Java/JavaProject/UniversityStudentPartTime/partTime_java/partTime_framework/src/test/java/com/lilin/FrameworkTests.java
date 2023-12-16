package com.lilin;

import com.lilin.utils.JwtUtil;
import com.lilin.utils.SecurityUtils;
import io.jsonwebtoken.Claims;

/**
 * Created by LiLin on 2022/9/20/15:24:35
 */
public class FrameworkTests {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1MmM5OTg4ZmJlMDk0MGEyOTk0M2QwOTkzNGIzZmYxOCIsInN1YiI6IjUiLCJpc3MiOiJzZyIsImlhdCI6MTY2Mzk4NTk4OSwiZXhwIjoxNjY2MDU5NTg5fQ.jRbX4HmU8ZG0c-FGk9YFAPPcy1lkoD7Uz7JvDiGhdyU";
        String token1 = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1MmM5OTg4ZmJlMDk0MGEyOTk0M2QwOTkzNGIzZmYxOCIsInN1YiI6IjUiLCJpc3MiOiJzZyIsImlhdCI6MTY2Mzk4NTk4OSwiZXhwIjoxNjY2MDU5NTg5fQ.jRbX4HmU8ZG0c-FGk9YFAPPcy1lkoD7Uz7JvDiGhdyU";
        String userId = null;
        try {
            userId = JwtUtil.parseJWT(token).getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userId);
        System.out.println(token.equals(token1));
    }
}

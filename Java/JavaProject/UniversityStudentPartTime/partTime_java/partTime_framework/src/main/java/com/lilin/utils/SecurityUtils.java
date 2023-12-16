package com.lilin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lilin.constant.GlobalConstant;
import com.lilin.constant.ResponseCodeEnum;
import com.lilin.entity.User;
import com.lilin.exception.CustomizeException;
import com.lilin.mapper.UserMapper;
import io.jsonwebtoken.lang.Strings;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by LiLin on 2022/9/20/15:20:56
 * partTime项目的工具类
 */
public class SecurityUtils {

    /**
     * @Author lilin
     * @Date 2022/9/20 15:22:57
     * @param password 未加密的密码
     * @Return
     * @Description 对密码进行加密，通过 md5
     */
    public static String LockPassword(String password, String salt) {
        assert password != null;
        return new Md5Hash(password, salt).toString();
    }

    /**
     * @Author lilin
     * @Date 2022/9/22 14:20:10
     * @Return
     * @Description 随机生成盐值
     */
    public static String getSalt() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    /**
     * @Author lilin
     * @Date 2022/9/27 10:32:21
     * @param token token
     * @param redisConnectionUtils redis连接工具类
     * @Return
     * @Description 从 redis 获取登录用户信息
     */
    public static User getLoginUserForToken(RedisConnectionUtils redisConnectionUtils, String token) throws Exception {
        //解析 token，获取用户 id
        String userId = JwtUtil.parseJWT(parseToken(token)).getSubject();

        //从 redis 中查询登录用户的信息
        String loginUser = redisConnectionUtils.getString(GlobalConstant.LOGIN_USER_KEY + userId);

        //将用户信息返回
        return JSON.parseObject(loginUser, User.class);
    }

    /**
     * @Author lilin
     * @Date 2022/9/27 10:53:51
     * @param token token
     * @Return
     * @Description 对前端传进来的 token 做个字符串匹配，取得干净的 token 值
     */
    public static String parseToken(String token) {
        if (token.contains("token"))
            return token.split(":")[1].replaceAll("\"", "").replace("}", "");
        else
            return token;
    }

    /**
     * @Author lilin
     * @Date 2022/9/29 14:56:46
     * @param token token
     * @Return
     * @Description 确定用户是否登录
     */
    public static User isUserLogin(String token, RedisConnectionUtils redisConnectionUtils) throws Exception {
        User loginUser = SecurityUtils.getLoginUserForToken(redisConnectionUtils, token);
        if (Objects.isNull(loginUser))
            throw new CustomizeException(ResponseCodeEnum.PLEASE_TO_LOGIN);

        return loginUser;
    }

    /**
     * @Author lilin
     * @Date 2022/9/29 14:56:46
     * @param userId 用户 id
     * @Return
     * @Description 确定用户是否存在
     */
    public static User isUserExists(Long userId, UserMapper userMapper) {
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getId, userId);
        User loginUser = userMapper.selectOne(userQueryWrapper);

        if (Objects.isNull(loginUser))
            throw new CustomizeException(ResponseCodeEnum.USER_NOT_EXIST);

        return loginUser;
    }

    public static <T>T parseJsonObject(JSONObject jsonObject, String jsonObjectKey, Class<T> obj) {
        return JSON.toJavaObject((JSONObject) jsonObject.get(jsonObjectKey), obj);
    }

}

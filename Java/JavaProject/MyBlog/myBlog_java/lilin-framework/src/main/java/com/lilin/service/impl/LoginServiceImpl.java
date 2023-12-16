package com.lilin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilin.annotation.SystemLog;
import com.lilin.constants.SystemConstants;
import com.lilin.entity.LoginUser;
import com.lilin.entity.User;
import com.lilin.enums.AppHttpCodeEnum;
import com.lilin.exception.SystemException;
import com.lilin.mapper.UserMapper;
import com.lilin.service.LoginService;
import com.lilin.utils.*;
import com.lilin.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by LiLin on 2022/9/13/14:34:39
 */
@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult<Object> login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //判断是否认证通过
        if (Objects.isNull(authenticate))
            throw new RuntimeException("用户名或密码错误");

        //获取 userId 生成 token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String token = JwtUtil.createJWT(userId);

        //将用户信息存入 redis
        redisCache.setCacheObject(SystemConstants.TOKEN_PRE_ADMIN + userId, loginUser);

        //将 token 封装返回
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResponseResult.okResult(map);
    }

    /**
     * @Author lilin
     * @Date 2022/9/10 13:27:27
     * @Return
     * @Description 获取用户信息
     */
    @Override
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult<Object> userInfo() {
        //获取当前用户 id
        Long userId = SecurityUtils.getUserId();
        //根据用户id 查询用户信息
        User user = getById(userId);
        //封装成 vo
        UserInfoVo userInfoVo = CopyBeanUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    /**
     * @Author lilin
     * @Date 2022/9/11 13:52:52
     * @param user 用户实体类
     * @Return
     * @Description 更新用户信息
     */
    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    /**
     * @Author lilin
     * @Date 2022/9/11 14:26:50
     * @param user 用户实体类
     * @Return
     * @Description 注册
     */
    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        if (!StringUtils.hasText(user.getUsername()))
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        if (!StringUtils.hasText(user.getPassword()))
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        if (!StringUtils.hasText(user.getEmail()))
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        if (!StringUtils.hasText(user.getNickName()))
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);

        //判断数据是否已经存在
        if (userExist(user.getUsername()))
            throw new SystemException(AppHttpCodeEnum.USER_EXIST);

        //对密码进行加密操作
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //存入数据库
        save(user);
        return ResponseResult.okResult();
    }

    /**
     * @Author lilin
     * @Date 2022/9/14 15:47:41
     * @Return
     * @Description 退出登录
     */
    @Override
    public void logout() {
        //获取用户 id
        Long userId = SecurityUtils.getUserId();
        //删除 redis 中的 token
        redisCache.deleteObject(SystemConstants.TOKEN_PRE_ADMIN + userId);
    }

    private Boolean userExist(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return count(queryWrapper) > 0;
    }
}

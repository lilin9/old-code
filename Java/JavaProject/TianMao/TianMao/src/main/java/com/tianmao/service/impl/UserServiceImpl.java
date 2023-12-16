package com.tianmao.service.impl;

import com.tianmao.mapper.UserMapper;
import com.tianmao.pojo.User;
import com.tianmao.service.UserService;
import com.tianmao.service.exception.*;
import com.tianmao.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by LiLin on 2022/04/10/15:34
 *
 * 用户业务模块的接口实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * @Author LiLin
     * @Date 2022/4/12 17:04
     * @Param username 用户名
     * @Param password 用户密码
     * @Description 根据用户名和密码实现登录操作
     */
    @Override
    public User login(String username, String password) {
        //查询用户信息
        User result = userMapper.selectUserByUsername(username);
        if (result == null)
            throw new UserNotFoundException("用户不存在");

        //查询盐值
        String salt = result.getSalt();
        //查询用户密码
        String oldPassword = result.getPassword();
        //对传入的密码进行MD5加密
        String newPassword = Md5Utils.getMd5Password(password, salt);
        //检查用户密码是否一致
        if (!newPassword.equalsIgnoreCase(oldPassword))
            throw new PasswordNotMatchException("用户密码不匹配");

        //判断用户是否被删除
        if (result.getIsDelete() == 1)
            throw new UserNotFoundException("用户已被删除");

        //根据uid、用户名、用户头像重新封装一个user用户
        //目的：减少数据传输的大小，提高前后端的数据传输效率
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        //返回user
        return user;
    }

    /**
     * @Author LiLin
     * @Date 2022/4/10 15:34
     * @Param User 用户实体类
     * @Description 实现用户注册功能
     */
    @Override
    public void register(User user) {
        String username = user.getUsername();
        Date date = new Date();

        //补全用户信息
        //用户是否被删除
        user.setIsDelete(0);
        //日志-创建人
        user.setCreatedUser(username);
        //日志-创建时间
        user.setCreatedTime(date);
        //日志-最后修改执行人
        user.setModifiedUser(username);
        //日志-最后修改时间
        user.setModifiedTime(date);

        //查看用户名是否已经存在，如果存在则抛出异常
        if (userMapper.selectUserByUsername(username) != null) {
            throw new UsernameDuplicateException("用户名已存在");
        }

        //对用户密码进行加密操作（通过MD5算法进行加密）
        //获取盐值（一个随机的字符串）
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将盐值保存到用户信息中
        user.setSalt(salt);
        //对用户密码进行MD5加密操作
        String lockPassword = Md5Utils.getMd5Password(user.getPassword(), salt);
        //更新用户数据中的密码
        user.setPassword(lockPassword);

        //注册用户信息
        Integer rows = userMapper.insert(user);
        //如果用户信息注册失败，即影响的行数不为1
        //抛出以下异常
        if (rows != 1) {
            throw new InsertException("用户注册失败");
        }
    }

    /**
     * @Author LiLin
     * @Date 2022/5/22 17:12
     * @Param uid 用户id
     * @Param newPassword 用户新密码
     * @Param oldPassword 用户旧密码
     * @Description 根据uid更改用户密码
     */
    @Override
    public void updatePassword(Integer uid, String newPassword, String oldPassword) {
        //根据用户的uid查询用户信息
        User user = userMapper.selectUserByUid(uid);

        //判断用户是否存在
        if (user == null) {
            //如果用户不存在
            //执行以下异常
            throw new UserNotFoundException("用户不存在");
        }

        //判断用户账户是否被删除
        if (user.getIsDelete() == 1) {
            //如果用户账户被删除
            //执行以下异常
            throw new UserNotFoundException("用户不存在");
        }

        //获取盐值
        String salt = user.getSalt();
        //对用户的旧密码进行md5加密
        String lockPassword = Md5Utils.getMd5Password(oldPassword, salt);
        //比较用户数据库密码和旧密码是否一致
        if (!lockPassword.equalsIgnoreCase(user.getPassword())) {
            //如果不一致抛出以下异常
            throw new PasswordNotMatchException("用户密码输入错误");
        }

        //如果用户没有被删除/用户存在
        //如果以上操作都无异常，执行修改用户密码操作
        Integer result = userMapper.updatePasswordByUid(uid, Md5Utils.getMd5Password(newPassword, salt), user.getUsername(), new Date());
        //判断修改用户密码操作是否正确被执行
        if (result == null) {
            //如果修改用户密码操作没有被正确执行
            //执行以下异常
            throw new UpdateException("修改用户信息过程中出现不可知错误");
        }
    }

    /**
     * @Author lilin
     * @Date 2022/6/2 17:21:16
     * @param username 用户名
     * @param newPassword 用户新密码
     * @Description 根据用户名重置密码
     */
    @Override
    public void resetPassword(String username, String newPassword) {
        User user = userMapper.selectUserByUsername(username);

        //判断用户是否存在
        if (user == null)
            //如果用户不存在
            //执行以下异常
            throw new UserNotFoundException("用户不存在");

        //判断用户账户是否被删除
        if (user.getIsDelete() == 1)
            //如果用户账户被删除
            //执行以下异常
            throw new UserNotFoundException("用户不存在");

        //对用户密码进行加密操作（通过MD5算法进行加密）
        //获取盐值（一个随机的字符串）
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将盐值保存到用户信息中
        user.setSalt(salt);
        //对用户密码进行MD5加密操作
        String lockPassword = Md5Utils.getMd5Password(user.getPassword(), salt);

        //如果用户没有被删除/用户存在
        //如果以上操作都无异常，执行修改用户密码操作
        Integer result = userMapper.updatePasswordByUsername(username, lockPassword, username, new Date());
        //判断修改用户密码操作是否正确被执行
        if (result == null) {
            //如果修改用户密码操作没有被正确执行
            //执行以下异常
            throw new UpdateException("修改用户信息过程中出现不可知错误");
        }
    }

    /**
     * @Author LiLin
     * @Date 2022/5/23 11:33
     * @Param uid 用户id
     * @return 返回查询到的用户信息
     * @Description 查询用户信息，根据uid
     */
    @Override
    public User selectUser(Integer uid) {
        User result = userMapper.selectUserByUid(uid);
        if (result == null || result.getIsDelete() ==1) {
            throw new UserNotFoundException("用户不存在");
        }

        //重新包装前端页面需要的数据
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    /**
     * @Author LiLin
     * @Date 2022/5/23 11:32
     * @Param uid 用户id
     * @Param username 用户名
     * @Param user 用户实体类
     * @Description 修改用户信息
     */
    @Override
    public void updateUserInfo(Integer uid, String username, User user) {
        User result = userMapper.selectUserByUid(uid);
        if (result == null || result.getIsDelete() ==1) {
            throw new UserNotFoundException("用户不存在");
        }

        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        //更新数据
        Integer rows = userMapper.updateUserInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新用户数据时出现未知错误");
        }
    }

    /**
     * @Author LiLin
     * @Date 2022/5/23 13:13
     * @Param uid 用户id
     * @Param avatar 头像地址
     * @Param username 用户名
     * @Description 根据uid更新用户头像地址
     */
    @Override
    public void updateAvatarByUid(Integer uid, String avatar, String username) {
        //根据uid获取用户信息
        User result = userMapper.selectUserByUid(uid);

        if (result == null || result.getIsDelete() == 1) {
            //如果用户不存在或者用户已经被删除，抛出以下异常
            throw new UserNotFoundException("用户不存在");
        }

        //更新用户头像
        Integer integer = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (integer != 1) {
            //如果更新头像失败，抛出以下异常
            throw new UpdateException("更新用户头像过程中出现未知错误");
        }
    }
}

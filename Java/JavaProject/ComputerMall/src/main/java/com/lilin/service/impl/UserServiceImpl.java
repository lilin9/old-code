package com.lilin.service.impl;

import com.lilin.mapper.UserMapper;
import com.lilin.pojo.User;
import com.lilin.service.UserService;
import com.lilin.service.exception.*;
import com.lilin.utils.Md5Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by LiLin on 2022/04/05/14:11
 * <p>
 * 用户业务层接口的实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * @return 如果返回值为null说明登录失败；如果返回用户信息说明登录成功
     * @author LiLin
     * @create 2022/4/5 14:04
     * @description 根据用户名和密码实现登录操作
     */
    @Override
    public User login(String username, String password) {
        //根据用户名查询用户数据是否存在
        User result = userMapper.selectUserByUsername(username);
        if (result == null) {
            //如果用户数据为空
            //抛出下列异常
            throw new UsernameNotFoundException("用户不存在");
        }

        //如果用户数据不为空
        //检查用户密码是否匹配
        //获取数据库中加密后的密码
        String oldPassword = result.getPassword();
        //先获取盐值，即用户信息中保存的盐值
        String salt = result.getSalt();
        //将用户密码按照相同规则就行md5加密
        String newPassword = Md5Password.getMd5Password(password, salt);
        //和用户传递过来的密码进行比较
        if (!newPassword.equalsIgnoreCase(oldPassword)) {
            //如果新旧密码不匹配
            throw new PasswordNotMatchException("密码错误");
        }

        //判断用户信息是否已被删除
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }

        //根据uid、用户名、用户头像重新封装一个user用户
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return user;
    }

    /**
     * @author LiLin
     * @create 2022/4/5 14:06
     * @description 实现用户注册操作
     */
    @Override
    public void register(User user) {
        //用户是否被删除
        user.setIsDelete(0);
        //日志-创建人
        user.setCreatedUser(user.getUsername());
        //日志-创建时间
        Date date = new Date();
        user.setCreatedTime(date);
        //日志-最后修改执行人
        user.setModifiedUser(user.getUsername());
        //日志-最后修改时间
        user.setModifiedTime(date);

        //判断用户注册信息是否可用
        String username = user.getUsername();
        if (userMapper.selectUserByUsername(username) == null) {
            //获取盐值（随机的字符串）
            String salt = UUID.randomUUID().toString().toUpperCase();
            //保存盐值到用户数据中
            user.setSalt(salt);
            //将密码进行加密操作
            String lockPassword = Md5Password.getMd5Password(user.getPassword(), salt);
            //将用户密码设置为加密后的密码
            user.setPassword(lockPassword);

            //如果用户名为空就插入用户信息
            Integer result = userMapper.insert(user);
            if (result != 1) {
                //如果出现用户插入失败则抛出以下异常
                throw new InsertException("在用户注册过程中出现不可知错误");
            }
        } else {
            //如果用户名不为空就抛出以下异常
            throw new UsernameDuplicateException("用户名已被占用");
        }
    }

    /**
     * @author LiLin
     * @create 2022/4/6 17:02
     * @description 根据用户uid修改用户密码
     */
    @Override
    public void updatePasswordByUid(Integer uid,
                                    String username,
                                    String newPassword,
                                    String oldPassword) {
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
        String lockPassword = Md5Password.getMd5Password(oldPassword, salt);
        //比较用户数据库密码和旧密码是否一致
        if (!lockPassword.equalsIgnoreCase(user.getPassword())) {
            //如果不一致抛出以下异常
            throw new PasswordNotMatchException("用户密码输入错误");
        }

        //如果用户没有被删除/用户存在
        //如果以上操作都无异常，执行修改用户密码操作
        Integer result = userMapper.updatePasswordByUid(uid, Md5Password.getMd5Password(newPassword, salt), username, new Date());
        //判断修改用户密码操作是否正确被执行
        if (result != 1) {
            //如果修改用户密码操作没有被正确执行
            //执行以下异常
            throw new UpdateException("更新用户数据出现不可知错误");
        }
    }

    /**
     * @author LiLin
     * @create 2022/4/7 15:27
     * @return 返回用户的信息
     * @description 根据uid查询用户信息
     */
    @Override
    public User getUserByUid(Integer uid) {
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
     * @author LiLin
     * @create 2022/4/7 15:43
     * @description 根据uid更新用户信息
     */
    @Override
    public void updateInfo(Integer uid, String username, User user) {
        User result = userMapper.selectUserByUid(uid);
        if (result == null || result.getIsDelete() ==1) {
            throw new UserNotFoundException("用户不存在");
        }

        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        //更新数据
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新用户数据时出现未知错误");
        }
    }

    /**
     * @Author lilin
     * @Date 2022/4/8 15:00
     * @Param uid 用户id
     * @Param avatar 头像
     * @Param username 修改人姓名
     * @Description 根据uid更新用户头像
     */
    @Override
    public void updateAvatar(Integer uid, String avatar, String username) {
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
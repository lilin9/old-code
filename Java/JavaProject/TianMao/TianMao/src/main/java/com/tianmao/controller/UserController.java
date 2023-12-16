package com.tianmao.controller;

import com.tianmao.controller.exception.codeException.CodeMismatchException;
import com.tianmao.controller.exception.fileException.*;
import com.tianmao.pojo.User;
import com.tianmao.service.UserService;
import com.tianmao.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by LiLin on 2022/04/10/16:24
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @PostMapping("register")
    public JsonResult<Void> register(User user) {
        //注册用户信息
        userService.register(user);
        //注册成功的话就返回OK
        //如果注册失败了，程序就会在userService中结束并抛出异常
        return new JsonResult<>(OK);
    }

    @PostMapping("login")
    public JsonResult<User> login(String password, String username, HttpSession session) {
        User user = userService.login(username, password);
        //将用户信息保存到session域中，以供之后使用
        session.setAttribute("username", user.getUsername());
        session.setAttribute("uid", user.getUid());
        return new JsonResult<>(OK, user);
    }

    @PostMapping("updatePassword")
    public JsonResult<Void> updatePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session) {
        userService.updatePassword(getUidFormSession(session),
                newPassword,
                oldPassword);
        return new JsonResult<>(OK);
    }

    @GetMapping("getUser")
    public JsonResult<User> getUser(HttpSession session) {
        return new JsonResult<>(OK, userService.selectUser(getUidFormSession(session)));
    }

    @GetMapping("updateUser")
    public JsonResult<Void> updateUser(User user, HttpSession session) {
        userService.updateUserInfo(getUidFormSession(session),
                getUsernameFromSession(session),
                user);
        return new JsonResult<>(OK);
    }

    /*
    MultipartFile 是 SpringMvc 提供的一个接口，这接口包装了获取的文件类型的数据（任何类型的文件都可以接收），
    因为 SpringBoot 又整合了 SpringMvc，所以只需要在处理请求方法的参数列表上声明一个参数类型为 MultipartFile
    类型的参数，SpringBoot 就会自动将前端传进来的文件数据赋值给这个参数
     */

    //设置上传的头像最大值
    private static final int AVATAR_MAX_SIZE = 100 * 1024 * 1024;
    //设置上传头像的类型
    private static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image/bmp");
    }

    @PostMapping("updateAvatar")
    public JsonResult<String> updateAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        //判断文件是否为空
        if (file.isEmpty())
            throw new FileEmptyException("文件为空");

        //判断文件大小是否超出限制
        if (file.getSize() > AVATAR_MAX_SIZE)
            throw new FileSizeException("文件大小超出了限制");

        //判断文件类型是否符合要求
        if (!AVATAR_TYPE.contains(file.getContentType()))
            throw new FileTypeException("文件类型不符合要求");

        //获取头像要保存的文件目录
        String src = null;
        try {
            src = new File("").getCanonicalPath() + "\\src\\main\\resources\\static\\images\\avatar";
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert src != null;
        File dir = new File(src);
        //如果文件目录不存在，就创建文件目录
        if (!dir.exists()) dir.mkdirs();

        //获取到文件名
        String originalFilename = file.getOriginalFilename();
        //获取到后缀
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //拼接获取文件名
        String filename = getUsernameFromSession(session) + suffix;
        try {
            //先获取一个空文件夹：new File(dir, filename)
            //然后保存头像文件写入到空文件夹中：file.transferTo()
            file.transferTo(new File(dir, filename));
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写过程中出现异常");
        } catch (FileStatusException e) {
            throw new FileStatusException("文件状态异常");
        }

        //头像路径只要返回相对路径
        String avatar = "images/avatar/" + filename;
        userService.updateAvatarByUid(
                getUidFormSession(session),
                avatar,
                getUsernameFromSession(session));
        return new JsonResult<>(OK, avatar);
    }

    //获取session中的uid值
    @GetMapping("getSession")
    public JsonResult<Integer> getSession(HttpSession session) {
        return new JsonResult<>(OK, getUidFormSession(session));
    }

    //清除session
    @GetMapping("clear")
    public JsonResult<Void> clear(HttpSession session) {
        session.removeAttribute("uid");
        session.removeAttribute("username");
        return new JsonResult<>(OK);
    }

    @PostMapping("reset")
    public JsonResult<Void> resetPassword(
            String username,
            String password,
            String code,
            HttpSession session) {
        //判断用户验证码是否正确
        if (!code.equals(session.getAttribute("code")))
            throw new CodeMismatchException("验证码不匹配");

        //重置密码
        userService.resetPassword(username, password);
        return new JsonResult<>(OK);
    }
}

package com.lilin.controller;

import com.lilin.controller.exception.*;
import com.lilin.pojo.User;
import com.lilin.service.UserService;
import com.lilin.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/04/05/15:52
 */
//@Controller
@RestController //@RestController = @Controller + @ResponseBody
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @PostMapping("register")
    //@ResponseBody //表示方法的返回值是JSON格式的
    public JsonResult<Void> register(User user) {
        //注册用户信息
        userService.register(user);
        //如果注册成功，返回 OK 状态码
        return new JsonResult<>(OK);
    }

    @PostMapping("login")
    public JsonResult<User> login(String username,
                                  String password,
                                  HttpSession session) {
        User data = userService.login(username, password);
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        return new JsonResult<>(OK, data);
    }

    @PostMapping("update_password")
    public JsonResult<Void> updatePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session
                                           ) {
        userService.updatePasswordByUid(getUidFromSession(session),
                getUsernameFromSession(session),
                newPassword,
                oldPassword);
        return new JsonResult<>(OK);
    }

    @GetMapping("get_user")
    public JsonResult<User> getUser(HttpSession session) {
        User data = userService.getUserByUid(getUidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @GetMapping("update_info")
    public JsonResult<Void> updateInfo(User user, HttpSession session) {
        userService.updateInfo(getUidFromSession(session),
                getUsernameFromSession(session),
                user);

        return new JsonResult<>(OK);
    }

    /*
    MultipartFile 是 SpringMvc 提供的一个接口，这接口包装了获取的文件类型的数据（任何类型的文件都可以接收），
    因为 SpringBoot 又整合了 SpringMvc，所以只需要在处理请求方法的参数列表上声明一个参数类型为 MultipartFile 类型的参数，
    SpringBoot 就会自动将前端传进来的文件数据赋值给这个参数
     */

    //设置上传的头像最大值
    private static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    //设置上传头像的类型
    private static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image/bmp");
    }

    @PostMapping("update_avatar")
    public JsonResult<String> updateAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        //判断文件是否为空
        if (file.isEmpty()) throw new FileEmptyException("文件为空");
        //判断文件大小是否超出限制
        if (file.getSize() > AVATAR_MAX_SIZE) throw new FileSizeException("文件大小超出了限制");
        //判断文件类型是否符合要求
        if (!AVATAR_TYPE.contains(file.getContentType())) throw new FileTypeException("文件类型不符合要求");

        //获取上传文件的上下文路径
        //String parent = session.getServletContext().getRealPath("avatar");
        String parent = "D:/Java/Project/ComputerMall/src/main/resources/static/images/avatar";
        System.out.println("获取上传文件的路径：" + parent);
        File dir = new File(parent);
        //如果文件目录不存在，就创建文件目录
        if (!dir.exists()) dir.mkdirs();

        //获取到文件名
        String originalFilename = file.getOriginalFilename();
        //获取到后缀
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //拼接获取文件名
        //先获取文件上传的时间戳
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmm");
        String now = format.format(date);

        String filename = now + "-" + originalFilename + suffix;
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
        String avatar = "../images/avatar" + filename;
        userService.updateAvatar(getUidFromSession(session), avatar, getUsernameFromSession(session));
        return new JsonResult<>(OK, avatar);
    }
}
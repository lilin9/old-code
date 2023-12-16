package org.soft.base.server;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.soft.base.annotation.Description;
import org.soft.base.dao.message.HumanDao;
import org.soft.base.enums.ResponseEnum;
import org.soft.base.model.Human;
import org.soft.base.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Objects;

@SessionAttributes({"human"})
@RestController
public class HumanServer {
    @Autowired
    @Qualifier("humanDao")
    private HumanDao humanDao = null;

    /**
     * @param human human
     * @Return
     * @Description 用户注册管理
     * @Author LILIN
     * @Date 2023/7/27 15:23:34
     */
    @PostMapping("/register")
    @Description(message = "用户注册管理")
    public ResponseResult<Object> humanRegisterServer(@RequestBody Human human) {
        boolean isExist = humanDao.humanRegisterMapper(human);
        //判断用户是否注册成功，成功就返回 登录成功响应信息，反之返回 登录失败响应信息
        return isExist ?
                new ResponseResult<>(ResponseEnum.REGISTER_SUCCESS) :
                new ResponseResult<>(ResponseEnum.REGISTER_FAILED);
    }

    /**
     * @param human human
     * @Return
     * @Description 用户登录服务
     * @Author LILIN
     * @Date 2023/7/27 15:24:31
     */
    @PostMapping("/login")
    @Description(message = "用户登录服务")
    public ResponseResult<Object> humanLoginServer(@RequestBody Human human,
                                                   HttpServletRequest request) {
        human = humanDao.humanLoginMapper(human);
        //存一份human数据到session里
        HttpSession session = request.getSession();
        session.setAttribute("human", human);

        //判断是否登录成功
        return human == null ?
                new ResponseResult<>(ResponseEnum.LOGIN_FAILED) :
                new ResponseResult<>(ResponseEnum.LOGIN_SUCCESS, human);
    }

    /**
     * @param human human
     * @Return
     * @Description 修改用户信息
     * @Author LILIN
     * @Date 2023/7/27 15:51:52
     */
    @PostMapping("/update")
    @Description(message = "修改用户信息")
    public ResponseResult<Object> humanUpdateServer(@RequestBody Human human) {
        //修改数据
        boolean isUpdate = humanDao.humanUpdateMapper(human);

        //判断是否修改成功
        return isUpdate ?
                new ResponseResult<>(ResponseEnum.UPDATE_HUMAN_SUCCESS) :
                new ResponseResult<>(ResponseEnum.UPDATE_HUMAN_FAILED);
    }

    /**
     * @param session       session
     * @param sessionStatus sessionStatus
     * @Return
     * @Description 退出登录
     * @Author LILIN
     * @Date 2023/7/27 15:57:31
     */
    @PostMapping("/getOut")
    @Description(message = "退出登录")
    public ResponseResult<Object> getOut(HttpSession session, SessionStatus sessionStatus) {
        session.removeAttribute("human");
        sessionStatus.setComplete();
        return new ResponseResult<>(ResponseEnum.SUCCESS);
    }

    /**
     * @param human human
     * @Return
     * @Description 根据id查询 human 数据
     * @Author LILIN
     * @Date 2023/7/28 17:17:13
     */
    @PostMapping("/getHuman")
    @Description(message = "根据id查询 human 数据")
    public ResponseResult<Object> getHumanById(@RequestBody Human human) {
        human = humanDao.humanById(human.getHumanId());
        return Objects.isNull(human) ?
                new ResponseResult<>(ResponseEnum.FAILED) :
                new ResponseResult<>(ResponseEnum.SUCCESS, human);
    }

}

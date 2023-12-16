package com.pcjp.crm.workbench.web.controller;

import com.pcjp.crm.commons.contants.Contans;
import com.pcjp.crm.commons.domain.retrunObject;
import com.pcjp.crm.commons.utils.DataUitls;
import com.pcjp.crm.workbench.domain.Admin;
import com.pcjp.crm.workbench.domain.TUser;
import com.pcjp.crm.workbench.servince.AdminService;
import com.pcjp.crm.workbench.servince.TUserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TUserController
 * @Description TODO
 * @Author Jiang
 * @Date 2022/5/31 12:42
 * @Version 1.0
 **/
@Controller
public class TUserController {

    @Autowired
    TUserService tUserService;

    @Autowired
    AdminService adminService;

    /**
     * 跳转到登录页面
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin() {
        //请求转发到登录页面
        return "settings/qx/user/login";
    }



    //请求转发到user.jsp页面
    @RequestMapping("/workbench/main/user.do")
    public String user(HttpServletRequest request){
        List<Admin> admins = adminService.selectAllAdmin();
        //把数据保存到request中
        request.setAttribute("adminsList",admins);
        return "workbench/User/user";
    }

    //    登录请求
    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody
    Object login(String loginAct, String loginPwd, String isRemPwd,
                 HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);
        //调用servince 方法

        Admin admin = adminService.selectUserByLoginActAndpwd(map);

        retrunObject retrunObject = new retrunObject();
        if (admin == null) {
            //登录失败，用户名或密码错误
            retrunObject.setCode(Contans.RETRUN_OBJECT_CODE_FALSE);
            retrunObject.setMessage("用户名或密码错误");
        } else {
            //登录成功
            retrunObject.setCode(Contans.RETRUN_OBJECT_CODE_SUCCESS);
            //把admin对象保存到session中
            session.setAttribute(Contans.SESSION_USER, admin);
            //如果用户需要记住密码，往外写cookie
            if ("true".equals(isRemPwd)) {
                Cookie c1 = new Cookie("loginAct", admin.getAdminuser());
                c1.setMaxAge(10 * 24 * 60 * 60);
                response.addCookie(c1);
                Cookie c2 = new Cookie("loginPwd", admin.getAdminpass());
                c2.setMaxAge(10 * 24 * 60 * 60);
                response.addCookie(c2);
            } else {
                //每次判断是否需要记住密码
                //把没有过期的cookie删除
                Cookie c1 = new Cookie("loginAct", "1");
                c1.setMaxAge(0);
                response.addCookie(c1);

                Cookie c2 = new Cookie("loginPwd", "1");
                c2.setMaxAge(0);
                response.addCookie(c2);
            }

        }
        return retrunObject;
    }

    //安全退出
    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response, HttpSession session) {
        //清空cookie
        Cookie c1 = new Cookie("loginAct", "1");
        c1.setMaxAge(0);
        response.addCookie(c1);

        Cookie c2 = new Cookie("loginPwd", "1");
        c2.setMaxAge(0);
        response.addCookie(c2);

        //销毁session
        session.invalidate();
        //转发到首页
        return "redirect:/"; //springmvc来实现重定向
    }


    //根据条件去查询用户相关信息，模糊查询
    @RequestMapping("/workbench/user/queryCountOfUsersByCondition.do")
    public @ResponseBody
    Object queryCountOfUsersByCondition(String username, String phone, String email
            , String createuser, int pageNo, int pageSize) throws ParseException {
        Map<String, Object> map = new HashMap();
        map.put("username", username);
        map.put("phone", phone);
        map.put("email", email);
        map.put("createdUser", createuser);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        //调用service 层的方法，查询数据


        List<TUser> tUsers = tUserService.queryAllByLimit(map);

        //根据查询结果，生成响应信息
        HashMap<String, Object> retMap = new HashMap<>();
        retMap.put("tUsers", tUsers);
        return retMap;
    }

    @RequestMapping("/workbench/user/saveCreateUser.do")
    public @ResponseBody
    Object saveCreateUser(TUser tUser) {
        retrunObject retrunObject = new retrunObject();
        tUser.setCreatedTime(DataUitls.formateDateTime(new Date()));
        try {
            //调servince方法，保存创建市场活动
            int ret = tUserService.insert(tUser);
            if (ret > 0) {
                retrunObject.setCode(Contans.RETRUN_OBJECT_CODE_SUCCESS);
            } else {
                retrunObject.setCode(Contans.RETRUN_OBJECT_CODE_FALSE);
                retrunObject.setMessage("系统繁忙,请稍后再试.....");
            }
        } catch (Exception e) {
            e.printStackTrace();

            retrunObject.setCode(Contans.RETRUN_OBJECT_CODE_FALSE);
            retrunObject.setMessage("系统繁忙,请稍后再试.....");
        }
        return retrunObject;
    }


    @RequestMapping("/workbench/user/deleteUserByIds.do")
    public @ResponseBody
    Object deleteUserByIds(String[] id) {
        retrunObject retrunObject = new retrunObject();
        try {
            //调用service方法删除活动
            int ret = tUserService.deleteById(id);
            if (ret > 0) {
                retrunObject.setCode(Contans.RETRUN_OBJECT_CODE_SUCCESS);
            } else {
                retrunObject.setCode(Contans.RETRUN_OBJECT_CODE_FALSE);
                retrunObject.setMessage("系统繁忙,请稍后重试....");
            }
        } catch (Exception e) {
            e.printStackTrace();
            retrunObject.setCode(Contans.RETRUN_OBJECT_CODE_FALSE);
            retrunObject.setMessage("系统繁忙,请稍后重试....");
        }
        return retrunObject;
    }


    @RequestMapping("/workbench/user/queryUserById.do")
    public @ResponseBody
    Object queryUserById(String id) {
        //调用方法查询
        TUser tUser = tUserService.queryById(id);

        //根据查询结果，返回响应信息
        return tUser;
    }


    @RequestMapping("/workbench/user/saveUser.do")
    public @ResponseBody
    Object saveUser(TUser tUser) {

        //调用service 方法
        retrunObject retrunObject = new retrunObject();
        try {

            int ret = tUserService.update(tUser);
            if (ret > 0) {
                retrunObject.setCode(Contans.RETRUN_OBJECT_CODE_SUCCESS);

            } else {
                retrunObject.setCode(Contans.RETRUN_OBJECT_CODE_FALSE);
                retrunObject.setMessage("系统繁忙,请稍后在试....");
            }
        } catch (Exception e) {
            e.printStackTrace();
            retrunObject.setCode(Contans.RETRUN_OBJECT_CODE_FALSE);
            retrunObject.setMessage("系统繁忙,请稍后在试....");
        }
        return retrunObject;
    }


    @RequestMapping("/workbench/user/exportUserAll.do")
    public void exportUserAll(HttpServletResponse response) throws IOException {
        //调用service方法
        List<TUser> userList = tUserService.queryallUser();

        //创建excel文件，并把activitylist写入到excel文件中
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("用户列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("uid");
        cell = row.createCell(1);

        cell.setCellValue("用户名");
        cell = row.createCell(2);


        cell.setCellValue("密码");
        cell = row.createCell(3);

        cell.setCellValue("盐值");
        cell = row.createCell(4);

        cell.setCellValue("电话号码");
        cell = row.createCell(5);

        cell.setCellValue("邮箱");
        cell = row.createCell(6);

        cell.setCellValue("盐值");
        cell = row.createCell(7);

        cell.setCellValue("创建者");
        cell = row.createCell(8);

        cell.setCellValue("创建时间");
        cell = row.createCell(9);

        cell.setCellValue("修改者");
        cell = row.createCell(10);


        //判断是否为空
        if (userList != null && userList.size() > 0) {
            //遍历activityList 创建HSSFRow
            TUser tUser = null;
            for (int i = 0; i < userList.size(); i++) {
                tUser = userList.get(i);

                //每遍历出一个User  生成一行
                row = sheet.createRow(i + 1);
                //每一行创建11列，每一列的数据从activity里面取
                cell = row.createCell(0);
                cell.setCellValue(tUser.getUid());
                cell = row.createCell(1);

                cell.setCellValue(tUser.getUsername());
                cell = row.createCell(2);


                cell.setCellValue(tUser.getPassword());
                cell = row.createCell(3);

                cell.setCellValue(tUser.getSalt());
                cell = row.createCell(4);

                cell.setCellValue(tUser.getPhone());
                cell = row.createCell(5);

                cell.setCellValue(tUser.getEmail());
                cell = row.createCell(6);

                cell.setCellValue(tUser.getSalt());
                cell = row.createCell(7);

                cell.setCellValue(tUser.getCreatedUser());
                cell = row.createCell(8);

                cell.setCellValue(tUser.getCreatedTime());
                cell = row.createCell(9);

                cell.setCellValue(tUser.getModifiedUser());
                cell = row.createCell(10);
            }
        }

        //生成excel 文件
        /*FileOutputStream os = new FileOutputStream("F:\\新建文件夹\\activityList.xls");
        wb.write(os);*/


        //文件下载 下载到客户端
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=UsersList.xls");
        ServletOutputStream out = response.getOutputStream();

        /*InputStream is = new FileInputStream("F:\\新建文件夹\\activityList.xls");

        byte[] buff= new byte[256];

        int len=0;

        while((len=is.read(buff))!=-1){
            out.write(buff,0,len);
        }
          is.close();
        */
        wb.write(out);
        wb.close();
        out.flush();
    }
}

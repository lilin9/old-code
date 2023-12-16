package com.pcjp.crm.workbench.web.controller;


import com.pcjp.crm.commons.contants.Contans;
import com.pcjp.crm.commons.domain.retrunObject;
import com.pcjp.crm.commons.utils.DataUitls;
import com.pcjp.crm.workbench.domain.Admin;
import com.pcjp.crm.workbench.domain.TOrder;
import com.pcjp.crm.workbench.domain.TProduct;
import com.pcjp.crm.workbench.servince.AdminService;
import com.pcjp.crm.workbench.servince.TOrderService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class TOrderController {
    @Autowired
    AdminService adminService;

    @Autowired
    TOrderService tOrderService;


    //请求转发到user.jsp页面
    @RequestMapping("/workbench/main/orderforgoods.do")
    public String goods(HttpServletRequest request){
        List<Admin> admins = adminService.selectAllAdmin();
        //把数据保存到request中
        request.setAttribute("adminsList",admins);

        return "workbench/orderforgoods/orderforgoods";
    }



    //根据条件去查询用户相关信息，模糊查询
    @RequestMapping("/workbench/order/queryCountOfOrderByCondition.do")
    public @ResponseBody
    Object queryCountOfOrderByCondition(String recvName
            , int pageNo, int pageSize) throws ParseException {
        Map<String, Object> map = new HashMap();

        map.put("recvName", recvName);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        //调用service 层的方法，查询数据

        List<TOrder> Orders = tOrderService.queryAllOrderByLimit(map);

        //根据查询结果，生成响应信息
        HashMap<String, Object> retMap = new HashMap<>();
        retMap.put("OrdersList", Orders);
        return retMap;
    }

    @RequestMapping("/workbench/order/queryOrderById.do")
    public @ResponseBody
    Object queryOrderById(String id) {
        //调用方法查询

        TOrder tOrder=tOrderService.queryById(id);

        //根据查询结果，返回响应信息
        return tOrder;
    }


    @RequestMapping("/workbench/order/saveCreateorder.do")
    public @ResponseBody
    Object saveCreateorder(TOrder tOrder) {
        retrunObject retrunObject = new retrunObject();

        try {
            //调servince方法，保存创建市场活动
            int ret = tOrderService.update(tOrder);
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


    @RequestMapping("/workbench/order/deleteorderByIds.do")
    public @ResponseBody
    Object deleteorderByIds(String[] id) {
        retrunObject retrunObject = new retrunObject();
        try {
            //调用service方法删除活动
            int ret = tOrderService.deleteById(id);
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


    @RequestMapping("/workbench/order/exportorderAll.do")
    public void exportorderAll(HttpServletResponse response) throws IOException {
        //调用service方法
        List<TOrder> tOrders=tOrderService.queryAllOrder();


        //创建excel文件，并把activitylist写入到excel文件中
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("订单列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("订单id");
        cell = row.createCell(1);

        cell.setCellValue("用户id");
        cell = row.createCell(2);


        cell.setCellValue("收货人姓名");
        cell = row.createCell(3);

        cell.setCellValue("收货人电话");
        cell = row.createCell(4);

        cell.setCellValue("收获详情地址");
        cell = row.createCell(5);

        cell.setCellValue("订单总价");
        cell = row.createCell(6);

        cell.setCellValue("订单状态");
        cell = row.createCell(7);

        cell.setCellValue("订单创建时间");
        cell = row.createCell(8);

        cell.setCellValue("创建者");
        cell = row.createCell(9);




        //判断是否为空
        if (tOrders != null && tOrders.size() > 0) {
            //遍历activityList 创建HSSFRow
           TOrder tOrder=null;
            for (int i = 0; i < tOrders.size(); i++) {
                tOrder = tOrders.get(i);

                //每遍历出一个User  生成一行
                row = sheet.createRow(i + 1);
                //每一行创建11列，每一列的数据从activity里面取
                cell = row.createCell(0);
                cell.setCellValue(tOrder.getOid());
                cell = row.createCell(1);

                cell.setCellValue(tOrder.getUid());
                cell = row.createCell(2);


                cell.setCellValue(tOrder.getRecvName());
                cell = row.createCell(3);

                cell.setCellValue(tOrder.getRecvPhone());
                cell = row.createCell(4);

                cell.setCellValue(tOrder.getRecvAddress());
                cell = row.createCell(5);

                cell.setCellValue(tOrder.getTotalPrice());
                cell = row.createCell(6);

                cell.setCellValue(tOrder.getStatus());
                cell = row.createCell(7);

                cell.setCellValue(tOrder.getCreatedTime());
                cell = row.createCell(8);

                cell.setCellValue(tOrder.getCreatedUser());
                cell = row.createCell(9);

            }
        }





        //文件下载 下载到客户端
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=OrderList.xls");
        ServletOutputStream out = response.getOutputStream();



        wb.write(out);
        wb.close();
        out.flush();
    }


}

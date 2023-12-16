package com.pcjp.crm.workbench.web.controller;

import com.pcjp.crm.commons.contants.Contans;
import com.pcjp.crm.commons.domain.retrunObject;
import com.pcjp.crm.commons.utils.DataUitls;
import com.pcjp.crm.commons.utils.UUIDUiles;
import com.pcjp.crm.workbench.domain.Admin;
import com.pcjp.crm.workbench.domain.TProduct;
import com.pcjp.crm.workbench.domain.TUser;
import com.pcjp.crm.workbench.servince.AdminService;
import com.pcjp.crm.workbench.servince.TProductService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @ClassName TProductController
 * @Description TODO
 * @Author Jiang
 * @Date 2022/6/2 14:30
 * @Version 1.0
 **/
@Controller
public class TProductController {

    @Autowired
    AdminService adminService;

    @Autowired
    TProductService tProductService;

    //请求转发到user.jsp页面
    @RequestMapping("/workbench/main/goods.do")
    public String goods(HttpServletRequest request){
        List<Admin> admins = adminService.selectAllAdmin();
        //把数据保存到request中
        request.setAttribute("adminsList",admins);

        return "workbench/goods/goodstest";
    }


    //根据条件去查询用户相关信息，模糊查询
    @RequestMapping("/workbench/product/queryCountOfUsersByCondition.do")
    public @ResponseBody
    Object queryCountOfUsersByCondition(String itemType, String title, String createdUser
            , int pageNo, int pageSize) throws ParseException {
        Map<String, Object> map = new HashMap();
        map.put("itemType", itemType);
        map.put("title", title);
        map.put("createdUser", createdUser);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        //调用service 层的方法，查询数据

        List<TProduct> tProducts = tProductService.queryAllProductByLimit(map);

        //根据查询结果，生成响应信息
        HashMap<String, Object> retMap = new HashMap<>();
        retMap.put("tProducts", tProducts);
        return retMap;
    }


    @RequestMapping("/workbench/product/saveCreateProduct.do")
    public @ResponseBody
    Object saveCreateProduct(TProduct tProduct) {
        retrunObject retrunObject = new retrunObject();

        tProduct.setCreatedTime(DataUitls.formateDateTime(new Date()));
        tProduct.setId((int)((Math.random()*9+1)*100000));


        try {
            //调servince方法，保存创建市场活动
            int ret = tProductService.insertProduct(tProduct);
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


    @RequestMapping("/workbench/product/deleteProductByIds.do")
    public @ResponseBody
    Object deleteProductByIds(String[] id) {
        retrunObject retrunObject = new retrunObject();
        try {
            //调用service方法删除活动
            int ret = tProductService.deleteById(id);
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

    @RequestMapping("/workbench/product/queryProductById.do")
    public @ResponseBody
    Object queryProductById(String id) {
        //调用方法查询
        TProduct tProduct = tProductService.queryById(id);

        //根据查询结果，返回响应信息
        return tProduct;
    }


    @RequestMapping("/workbench/product/saveProduct.do")
    public @ResponseBody
    Object saveProduct(TProduct tProduct) {

        //调用service 方法
        retrunObject retrunObject = new retrunObject();
        try {

            int ret = tProductService.update(tProduct);
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


    @RequestMapping("/workbench/product/exportProductAll.do")
    public void exportProductAll(HttpServletResponse response) throws IOException {
        //调用service方法
        List<TProduct> tProducts = tProductService.queryAllProduct();


        //创建excel文件，并把activitylist写入到excel文件中
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("商品列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("商品id");
        cell = row.createCell(1);

        cell.setCellValue("商品类别");
        cell = row.createCell(2);


        cell.setCellValue("商品标题");
        cell = row.createCell(3);

        cell.setCellValue("商品卖点");
        cell = row.createCell(4);

        cell.setCellValue("商品单价");
        cell = row.createCell(5);

        cell.setCellValue("商品库存");
        cell = row.createCell(6);

        cell.setCellValue("商品状态");
        cell = row.createCell(7);

        cell.setCellValue("创建时间");
        cell = row.createCell(8);

        cell.setCellValue("创建者");
        cell = row.createCell(9);




        //判断是否为空
        if (tProducts != null && tProducts.size() > 0) {
            //遍历activityList 创建HSSFRow
            TProduct tProduct = null;
            for (int i = 0; i < tProducts.size(); i++) {
                tProduct = tProducts.get(i);

                //每遍历出一个User  生成一行
                row = sheet.createRow(i + 1);
                //每一行创建11列，每一列的数据从activity里面取
                cell = row.createCell(0);
                cell.setCellValue(tProduct.getId());
                cell = row.createCell(1);

                cell.setCellValue(tProduct.getItemType());
                cell = row.createCell(2);


                cell.setCellValue(tProduct.getTitle());
                cell = row.createCell(3);

                cell.setCellValue(tProduct.getSellPoint());
                cell = row.createCell(4);

                cell.setCellValue(tProduct.getPrice());
                cell = row.createCell(5);

                cell.setCellValue(tProduct.getNum());
                cell = row.createCell(6);

                cell.setCellValue(tProduct.getStatus());
                cell = row.createCell(7);

                cell.setCellValue(tProduct.getCreatedTime());
                cell = row.createCell(8);

                cell.setCellValue(tProduct.getCreatedUser());
                cell = row.createCell(9);

            }
        }

     /*   //生成excel 文件
        FileOutputStream os = new FileOutputStream("F:\\新建文件夹\\activityList.xls");
        wb.write(os);*/


        //文件下载 下载到客户端
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=ProductList.xls");
        ServletOutputStream out = response.getOutputStream();

       /* InputStream is = new FileInputStream("F:\\新建文件夹\\activityList.xls");

        byte[] buff= new byte[256];

        int len=0;

        while((len=is.read(buff))!=-1){
            out.write(buff,0,len);
        }
          is.close();*/

        wb.write(out);
        wb.close();
        out.flush();
    }
}

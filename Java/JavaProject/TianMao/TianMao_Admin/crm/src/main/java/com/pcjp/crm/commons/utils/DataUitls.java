package com.pcjp.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DataUitls
 * @Description 日期格式工具类
 * @Author Jiang
 * @Date 2022/5/4 20:58
 * @Version 1.0
 **/
public class DataUitls {
//    对制定Data对象进行格式化
    public static  String formateDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}

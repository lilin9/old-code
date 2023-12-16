package com.pcjp.crm.commons.utils;

import java.util.UUID;

/**
 * @ClassName UUIDUiles
 * @Description UUID随机生成
 * @Author Jiang
 * @Date 2022/5/8 15:30
 * @Version 1.0
 **/
public class UUIDUiles {
    public static String getUUID (){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

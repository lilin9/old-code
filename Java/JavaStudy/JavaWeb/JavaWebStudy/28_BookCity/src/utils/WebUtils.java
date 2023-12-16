package utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {

    /**
     * @author MrLi
     * @create 2022/1/4 12:37
     * @return 泛型T
     * @description 把Map中的值注入到对应的JavaBean属性中。
     */
    public static <T> T copyParamToBean( Map value , T bean ){
        try {
            System.out.println("注入之前：" + bean);

            //把所有请求的参数都注入到user对象中
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * @author MrLi
     * @create 2022/1/4 12:40
     * @return 返回一个整形，如果转换失败则返回默认值
     * @description 用于转换 String类型 到 int类型 的工具类
     */
    public static Integer parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}

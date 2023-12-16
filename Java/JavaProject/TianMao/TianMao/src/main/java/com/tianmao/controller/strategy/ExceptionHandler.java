package com.tianmao.controller.strategy;

import com.tianmao.utils.JsonResult;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by LiLin on 2022/05/20/11:16
 *
 * 定义策略接口，通常包含两个方法：获取策略类型的方法和处理策略业务逻辑的方法
 */
public interface ExceptionHandler extends InitializingBean {//InitializingBean：凡是继承该接口的类，在初始化bean的时候都会执行该方法
    JsonResult<Void> run(Throwable e);
}

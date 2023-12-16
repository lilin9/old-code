package com.tianmao.controller.strategy.strategyImpl;

import com.tianmao.controller.strategy.ExceptionFactory;
import com.tianmao.controller.strategy.ExceptionHandler;
import com.tianmao.controller.strategy.strategyImpl.father.BaseException;
import com.tianmao.utils.JsonResult;
import org.springframework.stereotype.Component;

/**
 * Created by LiLin on 2022/05/20/12:44
 *
 * 用户名被占用异常实现
 */
@Component
public class UsernameDuplicateExceptionImpl extends BaseException implements ExceptionHandler {
    @Override
    public JsonResult<Void> run(Throwable e) {
        return getResult(4000, "用户名已被占用", e);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ExceptionFactory.registerException("UsernameDuplicateException", this);
    }
}

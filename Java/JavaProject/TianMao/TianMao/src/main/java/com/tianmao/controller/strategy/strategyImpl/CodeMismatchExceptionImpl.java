package com.tianmao.controller.strategy.strategyImpl;

import com.tianmao.controller.strategy.ExceptionFactory;
import com.tianmao.controller.strategy.ExceptionHandler;
import com.tianmao.controller.strategy.strategyImpl.father.BaseException;
import com.tianmao.utils.JsonResult;
import org.springframework.stereotype.Component;

/**
 * Created by LiLin on 2022/6/2/20:29:57
 *
 * 验证码不匹配
 */
@Component
public class CodeMismatchExceptionImpl extends BaseException implements ExceptionHandler {
    @Override
    public JsonResult<Void> run(Throwable e) {
        return getResult(3000, "验证码不匹配", e);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ExceptionFactory.registerException("CodeMismatchException", this);
    }
}

package com.tianmao.controller.strategy.strategyImpl;

import com.tianmao.controller.strategy.ExceptionFactory;
import com.tianmao.controller.strategy.ExceptionHandler;
import com.tianmao.controller.strategy.strategyImpl.father.BaseException;
import com.tianmao.utils.JsonResult;
import org.springframework.stereotype.Component;

/**
 * Created by LiLin on 2022/05/20/12:51
 *
 * 权限异常访问异常实现
 */
@Component
public class AccessDeniedExceptionImpl extends BaseException implements ExceptionHandler {
    @Override
    public JsonResult<Void> run(Throwable e) {
        return getResult(4004, "权限异常访问", e);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ExceptionFactory.registerException("AccessDeniedException", this);
    }
}

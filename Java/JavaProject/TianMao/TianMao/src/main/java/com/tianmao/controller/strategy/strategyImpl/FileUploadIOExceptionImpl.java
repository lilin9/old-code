package com.tianmao.controller.strategy.strategyImpl;

import com.tianmao.controller.strategy.ExceptionFactory;
import com.tianmao.controller.strategy.ExceptionHandler;
import com.tianmao.controller.strategy.strategyImpl.father.BaseException;
import com.tianmao.utils.JsonResult;

/**
 * Created by LiLin on 2022/05/23/13:32
 */
public class FileUploadIOExceptionImpl extends BaseException implements ExceptionHandler {
    @Override
    public JsonResult<Void> run(Throwable e) {
        return getResult(8004, "文件上传失败", e);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ExceptionFactory.registerException("FileUploadIOException", this);
    }
}

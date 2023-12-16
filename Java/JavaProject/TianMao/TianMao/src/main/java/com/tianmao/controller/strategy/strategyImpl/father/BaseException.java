package com.tianmao.controller.strategy.strategyImpl.father;

import com.tianmao.utils.JsonResult;

/**
 * Created by LiLin on 2022/05/20/21:38
 */
public abstract class BaseException {
    public final JsonResult<Void> getResult(Integer state, String message, Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        result.setState(state);
        result.setMessage(message);
        return result;
    }
}

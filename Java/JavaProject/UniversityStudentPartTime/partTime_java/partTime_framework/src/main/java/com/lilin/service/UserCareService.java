package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.entity.UserCare;
import com.lilin.vo.CareDetailVo;
import com.lilin.vo.UserCareDetailVo;

import java.util.List;

/**
 * Created by LiLin on 2022/10/6/15:53:58
 */
public interface UserCareService extends IService<UserCare> {

    List<Long> getUserCares(String token);

    void isCare(Long careId, String token, String isDelete);
    List<UserCareDetailVo> getUserCareDetails(String token);

    List<CareDetailVo> getAllCares();

    void updateCare(Long id, String isDelete);
}

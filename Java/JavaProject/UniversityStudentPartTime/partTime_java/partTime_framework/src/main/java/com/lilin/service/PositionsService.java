package com.lilin.service;

import com.lilin.entity.CompJobInfo;
import com.lilin.vo.PositionsDetailVo;
import com.lilin.vo.PositionsVo;
import com.lilin.vo.listVo.PositionsDetailListVo;

import java.util.List;

/**
 * 职位信息 服务接口
 *
 * @author makejava
 * @since 2022-09-24 11:13:15
 */
public interface PositionsService {
    List<PositionsDetailVo> getAllPositions();

    PositionsDetailListVo getAllPositions(String query, Long pageNum, Long pageSize);

    PositionsDetailVo getPositionsDetail(Long id);

    List<PositionsDetailVo> getPositions(String column, String positionsName);

    void addPositions(String token, CompJobInfo compJobInfo);

    List<PositionsVo> getPublishPositions(Long userId);

    void addPositionsForAdmin(CompJobInfo positionsData, String userName);

    void updatePositions(Long positionsId, Integer isAvailable);

    void deletePositions(Long positionsId);
}


package com.lilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilin.entity.CollectRecord;
import com.lilin.vo.CollectDetailVo;
import com.lilin.vo.CollectRecordVo;
import com.lilin.vo.PositionsVo;

import java.util.List;

/**
 * 收藏夹 服务接口
 *
 * @author makejava
 * @since 2022-09-26 09:16:54
 */
public interface CollectRecordService extends IService<CollectRecord> {

    List<CollectRecordVo> selectUserCollect(String token);

    void isCollect(Long jobId, String token, String isDelete);

    List<PositionsVo> getUserCollects(String token);

    List<CollectDetailVo> getAllCollects();

    List<CollectDetailVo> searchCollect(String query, Long pageNum, Long pageSize);

    void updateCollect(CollectDetailVo updateCollectData);

    void deleteCollectById(Long collectId);
}


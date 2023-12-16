package com.lilin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lilin.entity.CollectRecord;
import com.lilin.vo.CollectDetailVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 收藏夹 数据库访问层
 *
 * @author makejava
 * @since 2022-09-26 09:16:54
 */
@Mapper
public interface CollectRecordMapper extends BaseMapper<CollectRecord> {
    List<CollectDetailVo> getAllCollects();

    List<CollectDetailVo> getAllCollectsByUserIds(List<Long> userIds);

    List<CollectDetailVo> getAllCollectsByJobIds(List<Long> jobIds);
}


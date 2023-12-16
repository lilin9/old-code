package com.lilin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LiLin on 2022/9/26/09:40:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectRecordVo {
    //用户 id
    private Long userId;
    //收藏的工作
    private Long jobId;
}

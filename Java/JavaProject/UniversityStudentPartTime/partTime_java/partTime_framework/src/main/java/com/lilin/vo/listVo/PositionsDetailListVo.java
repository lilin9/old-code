package com.lilin.vo.listVo;

import com.lilin.vo.PositionsDetailVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by LiLin on 2022/10/10/14:48:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionsDetailListVo {
    private List<PositionsDetailVo> positionsDetailVos;
    private Long total;
}

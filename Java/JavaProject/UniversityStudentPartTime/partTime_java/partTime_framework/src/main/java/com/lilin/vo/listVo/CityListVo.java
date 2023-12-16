package com.lilin.vo.listVo;

import com.lilin.vo.CityVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by LiLin on 2022/10/9/18:08:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityListVo {
    private List<CityVo> cityVos;
    private Long total;
}

package com.lilin.controller;

import com.alibaba.fastjson.JSONObject;
import com.lilin.constant.GlobalConstant;
import com.lilin.constant.ResponseCodeEnum;
import com.lilin.entity.CompJobInfo;
import com.lilin.exception.CustomizeException;
import com.lilin.service.PositionsService;
import com.lilin.utils.ResponseResult;
import com.lilin.utils.SecurityUtils;
import com.lilin.vo.PositionsDetailVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Created by LiLin on 2022/9/24/15:40:27
 * 职位 控制层
 */
@RestController
@RequestMapping("positions")
public class PositionsController {
    private final PositionsService positionsService;

    /*
    通过构造器实现依赖注入
     */
    public PositionsController(PositionsService positionsService) {
        this.positionsService = positionsService;
    }

    /**
     * @Author lilin
     * @Date 2022/9/24 15:46:00
     * @Return
     * @Description 获取所有职位信息
     */
    @GetMapping("/")
    public ResponseResult<Object> positions() {
        return ResponseResult.success(
                positionsService.getAllPositions()
        );
    }

    /**
     * @Author lilin
     * @Date 2022/9/25 14:50:26
     * @param id 工作 id
     * @Return
     * @Description 获取工作详情信息
     */
    @GetMapping("/{positionId}")
    public ResponseResult<Object> positions(@PathVariable("positionId") Long id) {
        return ResponseResult.success(positionsService.getPositionsDetail(id));
    }

    /**
     * @Author lilin
     * @Date 2022/9/29 15:43:26
     * @param searchContent 搜索内容
     * @Return
     * @Description 根据用户输入的搜索内容查询职位信息
     */
    @GetMapping("/search")
    public ResponseResult<Object> search(@RequestParam("searchContent") String searchContent) {
        //先根据 职位名称 查询职位信息
        List<PositionsDetailVo> positionsDetailVoList = positionsService.getPositions(
                GlobalConstant.POSITIONS_NAME_COLUMN, searchContent
        );

        //如果查询不为空，返回查询结果
        if (!Objects.isNull(positionsDetailVoList))
            return ResponseResult.success(positionsDetailVoList);

        //如果查询为空，继续根据 城市名 查询职位信息
        positionsDetailVoList = positionsService.getPositions(
                GlobalConstant.CITY_NAME, searchContent
        );

        //再次判断查询结果是否为空，为空就抛出异常
        if (!Objects.isNull(positionsDetailVoList))
            return ResponseResult.success(positionsDetailVoList);

        //如果两次查询都为空，就抛出异常
        throw new CustomizeException(ResponseCodeEnum.SEARCH_POSITIONS_FAIL);
    }

    /**
     * @Author lilin
     * @Date 2022/10/2 17:19:20
     * @param token token
     * @param compJobInfo JSON格式的职位实体类
     * @Return
     * @Description 发布职位信息
     */
    @PutMapping("/publishing/{token}")
    public ResponseResult<Object> publishingPositions(@PathVariable("token") String token,
                                                      @RequestBody JSONObject compJobInfo) {
        positionsService.addPositions(
                token,
                //对JSON格式的数据进行解析，取得 CompJobInfo 实体类格式的数据
//                JSON.toJavaObject((JSONObject) compJobInfo.get("compJobInfo"), CompJobInfo.class)
                SecurityUtils.parseJsonObject(compJobInfo, "compJobInfo", CompJobInfo.class)
        );
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/9/29 14:44:45
     * @param userId 用户 id
     * @Return
     * @Description 获取用户发布的职位信息
     */
    @GetMapping("/publishPositions/{userId}")
    public ResponseResult<Object> publishPositions(@PathVariable("userId") Long userId) {
        return ResponseResult.success(positionsService.getPublishPositions(userId));
    }

}

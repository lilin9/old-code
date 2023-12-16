package com.lilin.controller;

import com.alibaba.fastjson.JSONObject;
import com.lilin.service.CollectRecordService;
import com.lilin.utils.ResponseResult;
import com.lilin.utils.SecurityUtils;
import com.lilin.vo.CollectDetailVo;
import io.jsonwebtoken.lang.Strings;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiLin on 2022/10/8/14:11:57
 * 收藏夹控制层
 */
@RestController
@RequestMapping("collect")
public class CollectController {
    private final CollectRecordService collectRecordService;
    public CollectController(CollectRecordService collectRecordService) {
        this.collectRecordService = collectRecordService;
    }

    /**
     * @Author lilin
     * @Date 2022/10/6 15:45:00
     * @param query 查询内容
     * @param pageNum 分页数量
     * @param pageSize 分页大小
     * @Return
     * @Description 查询收藏信息
     */
    @GetMapping("/")
    public ResponseResult<Object> getCollects(String query, Long pageNum, Long pageSize) {
        //判断用户是否输入 搜索内容
        if (!Strings.hasText(query))
            //如果没有内容，就查询所有收藏
            return ResponseResult.success(collectRecordService.getAllCollects());

        //否则就进行条件查询
        return ResponseResult.success(collectRecordService.searchCollect(query, pageNum, pageSize));
    }

    /**
     * @Author lilin
     * @Date 2022/10/9 10:53:00
     * @param collectId 收藏 id
     * @param updateCollectData 需要修改的职位收藏信息
     * @Return
     * @Description 修改用户收藏信息
     */
    @PutMapping("/{collectId}")
    public ResponseResult<Object> updateCollect(@PathVariable("collectId") Long collectId,
                                                @RequestBody JSONObject updateCollectData) {

        CollectDetailVo collectDetailVo = SecurityUtils.parseJsonObject(updateCollectData, "updateCollectData", CollectDetailVo.class);
        collectDetailVo.setId(collectId);
        collectRecordService.updateCollect(collectDetailVo);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/10/9 13:11:21
     * @param collectId 收藏 id
     * @Return
     * @Description 根据 id 删除用户收藏
     */
    @DeleteMapping("/{collectId}")
    public ResponseResult<Object> deleteCollect(@PathVariable("collectId") Long collectId) {
        collectRecordService.deleteCollectById(collectId);
        return ResponseResult.success();
    }
}

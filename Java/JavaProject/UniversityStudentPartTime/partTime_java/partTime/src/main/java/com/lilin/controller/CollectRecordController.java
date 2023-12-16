package com.lilin.controller;

import com.lilin.constant.GlobalConstant;
import com.lilin.service.CollectRecordService;
import com.lilin.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;


/**
 * (CollectRecord)表控制层
 *
 * @author makejava
 * @since 2022-09-26 09:16:53
 */
@RestController
@RequestMapping("collect")
public class CollectRecordController {
    private final CollectRecordService collectRecordService;

    /*
    通过构造器实现依赖注入
     */
    public CollectRecordController(CollectRecordService collectRecordService) {
        this.collectRecordService = collectRecordService;
    }

    /**
     * @param token token
     * @Author lilin
     * @Date 2022/9/26 09:32:39
     * @Return
     * @Description 查询用户收藏夹
     */
    @PostMapping("/selectUserCollect")
    public ResponseResult<Object> selectUserCollect(@RequestBody String token) {
        return ResponseResult.success(
                collectRecordService.selectUserCollect(token)
        );
    }

    /**
     * @param jobId 职位id
     * @param token token
     * @Author lilin
     * @Date 2022/9/26 13:37:04
     * @Return
     * @Description 收藏当前职位
     */
    @PutMapping("/addCollect/{id}")
    public ResponseResult<Object> addCollect(@PathVariable("id") Long jobId, @RequestBody String token) {
        collectRecordService.isCollect(jobId, token, GlobalConstant.NO_DELETE);
        return ResponseResult.success();
    }

    /**
     * @param jobId 职位id
     * @param token token
     * @Author lilin
     * @Date 2022/9/26 13:37:04
     * @Return
     * @Description 取消收藏当前职位
     */
    @DeleteMapping("/cancelCollect/{id}")
    public ResponseResult<Object> cancelCollect(@PathVariable("id") Long jobId, @RequestBody String token) {
        collectRecordService.isCollect(jobId, token, GlobalConstant.IS_DELETE);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/9/29 10:40:50
     * @param token token
     * @Return
     * @Description 获取用户收藏
     */
    @GetMapping("/collectDetail/{token}")
    public ResponseResult<Object> collectDetail(@PathVariable("token") String token) {
        return ResponseResult.success(collectRecordService.getUserCollects(token));
    }
}


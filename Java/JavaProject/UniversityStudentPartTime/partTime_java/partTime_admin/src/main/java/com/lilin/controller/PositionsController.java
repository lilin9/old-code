package com.lilin.controller;

import com.lilin.entity.CompJobInfo;
import com.lilin.service.PositionsService;
import com.lilin.utils.ResponseResult;
import io.jsonwebtoken.lang.Strings;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * (CompJobInfo)表控制层
 *
 * @author makejava
 * @since 2022-10-10 13:58:14
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
     * @Date 2022/10/10 14:00:42
     * @param query 查询内容
     * @param pageNum 分页数量
     * @param pageSize 分页大小
     * @Return
     * @Description 获取所有职位信息
     */
    @GetMapping("/")
    public ResponseResult<Object> getAllPositions(String query, Long pageNum, Long pageSize) {
        //如果前端没有传任何参数
        if (!Strings.hasText(query) && Objects.isNull(pageNum) && Objects.isNull(pageSize))
            return ResponseResult.success(positionsService.getAllPositions());
        return ResponseResult.success(positionsService.getAllPositions(query, pageNum, pageSize));
    }

    /**
     * @Author lilin
     * @Date 2022/10/11 16:08:02
     * @param positionsData 职位信息数据
     * @param userName 用户名
     * @Return
     * @Description 添加职位信息
     */
    @PostMapping("/{userName}")
    public ResponseResult<Object> addPositions(@RequestBody CompJobInfo positionsData,
                                               @PathVariable("userName") String userName) {
        positionsService.addPositionsForAdmin(positionsData, userName);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/10/12 13:49:20
     * @param positionsId 职位id
     * @param isAvailable 是否启用
     * @Return
     * @Description 修改职位信息
     */
    @PutMapping("/{positionsId}/{isAvailable}")
    public ResponseResult<Object> updatePositions(@PathVariable("positionsId") Long positionsId,
                                                  @PathVariable("isAvailable") Integer isAvailable) {
        positionsService.updatePositions(positionsId, isAvailable);
        return ResponseResult.success();
    }

    /**
     * @Author lilin
     * @Date 2022/10/12 15:08:07
     * @param positionsId 职位 id
     * @Return
     * @Description 删除职位信息
     */
    @DeleteMapping("/{positionsId}")
    public ResponseResult<Object> deletePositions(@PathVariable("positionsId") Long positionsId) {
        positionsService.deletePositions(positionsId);
        return ResponseResult.success();
    }
}


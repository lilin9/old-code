package com.lilin.controller;



import com.lilin.service.UserCareService;
import com.lilin.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * (UserCare)表控制层
 *
 * @author makejava
 * @since 2022-10-09 13:53:32
 */
@RestController
@RequestMapping("userCare")
public class UserCareController {
    private final UserCareService userCareService;
    /*
    通过构造器实现依赖注入
    */
    public UserCareController(UserCareService userCareService) {
        this.userCareService = userCareService;
    }

    /**
     * @Author lilin
     * @Date 2022/10/9 14:05:49
     * @Return
     * @Description 获取所有的关注信息
     */
    @GetMapping("/")
    public ResponseResult<Object> getAllCares() {
        return ResponseResult.success(userCareService.getAllCares());
    }

    /**
     * @Author lilin
     * @Date 2022/10/9 16:09:22
     * @param id 关注表id
     * @param isDelete 是否删除
     * @Return
     * @Description 修改用户关注表
     */
    @PutMapping("/{id}/{isDelete}")
    public ResponseResult<Object> updateCare(@PathVariable("id") Long id,
                                             @PathVariable("isDelete") String isDelete) {
        userCareService.updateCare(id, isDelete);
        return ResponseResult.success();
    }
}


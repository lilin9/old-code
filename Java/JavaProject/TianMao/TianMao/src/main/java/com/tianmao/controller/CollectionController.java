package com.tianmao.controller;

import com.tianmao.pojo.Collection;
import com.tianmao.service.CollectionService;
import com.tianmao.utils.JsonResult;
import com.tianmao.vo.CollectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by LiLin on 2022/05/17/14:47
 * <p>
 * 收藏夹控制层
 */
@RestController
@RequestMapping("collection")
public class CollectionController extends BaseController {
    @Autowired
    CollectionService collectionService;

    @PostMapping("save")
    public JsonResult<Void> save(Integer pid, HttpSession session) {
        collectionService.insertToCollection(getUidFormSession(session), pid);
        return new JsonResult<>(OK);
    }

    @PostMapping({"", "/"})
    public JsonResult<List<CollectionVo>> getAllCollectionVo(HttpSession session) {
        return new JsonResult<>(OK, collectionService.
                selectCollectionVoByUid(getUidFormSession(session)));
    }

    @GetMapping("delete")
    public JsonResult<Void> deleteCollection(Integer pid, HttpSession session) {
        collectionService.deleteCollection(pid, getUidFormSession(session));
        return new JsonResult<>(OK);
    }

    /**
     * @Author lilin
     * @Date 2022/5/31 14:09:54
     * @Description 查询收藏夹是否存在，如果收藏夹存在就返回1，如果收藏夹不存在就返回0
     */
    @GetMapping("get")
    public JsonResult<Integer> getCollection(Integer pid) {
        //查询收藏夹信息
        Collection result = collectionService.selectCollection(pid);
        //如果收藏夹不存在，返回0
        if (result == null) {
            return new JsonResult<>(OK, 0);
        }
        //如果收藏夹存在，返回1
        return new JsonResult<>(OK, 1);
    }
}

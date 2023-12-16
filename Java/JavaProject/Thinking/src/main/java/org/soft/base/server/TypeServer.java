package org.soft.base.server;

import org.soft.base.annotation.Description;
import org.soft.base.dao.message.TypeDao;
import org.soft.base.enums.ResponseEnum;
import org.soft.base.model.ResponseResult;
import org.soft.base.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeServer {

    @Autowired
    @Qualifier("typeDao")
    private TypeDao typeDao ;

    @RequestMapping("/typeList")
    @Description(message = "获取文章类别列表")
    public ResponseResult<Object> typeListCtrl(){
        List<Type> typeList = typeDao.typeList();
        return new ResponseResult<>(ResponseEnum.SUCCESS, typeList);
    }

    @RequestMapping("/typeIssue")
    @Description(message = "创建文章类别")
    public boolean typeCreateCtrl(Type type){
        boolean b = typeDao.typeIssue(type);
        return b;
    }

    @RequestMapping("/typeDel/{typeId}")
    @Description(message = "根据类型 id 删除文章类别")
    public boolean typeDelCtrl(@PathVariable("typeId") int typeId){
        boolean b = typeDao.typeDelById(typeId);
        return b;
    }

    @RequestMapping("/byId/{typeId}")
    @Description(message = "根据类型 id 查询文章类别")
    public Type typeByIdCtrl(@PathVariable("typeId") int typeId){
        Type type = typeDao.typeById(typeId);
        return type;
    }
}

package org.soft.base.dao.message.impl;

import org.soft.base.dao.message.TypeDao;
import org.soft.base.mapper.TypeDaoMapper;
import org.soft.base.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("typeDao")
public class TypeDaoImplements implements TypeDao {

    @Autowired
    private TypeDaoMapper typeDaoMapper;
    @Override
    public boolean typeIssue(Type type) {
        boolean b = typeDaoMapper.typeIssue(type);
        return false;
    }

    @Override
    public List<Type> typeList() {
        List<Type> types = typeDaoMapper.typeList();
        return types;
    }

    @Override
    public boolean typeDelById(int typeId) {
        boolean b = typeDaoMapper.typeDelById(typeId);
        return b;
    }

    @Override
    public Type typeById(int typeId) {
        Type type = typeDaoMapper.typeById(typeId);
        return type;
    }


}

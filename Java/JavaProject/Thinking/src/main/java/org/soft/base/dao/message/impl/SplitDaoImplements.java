package org.soft.base.dao.message.impl;

import org.soft.base.dao.message.SplitDao;
import org.springframework.stereotype.Component;

@Component("splitDao")
public class SplitDaoImplements implements SplitDao {

    @Override
    public int allPage(int rows , int size) {
        int value = rows /size;
        if (rows % size != 0) {
            value++;
        }
        return value;
    }

    @Override
    public int rowBegin(int currentPage , int size) {
        int value = (currentPage - 1) * size;
        return value;
    }
}

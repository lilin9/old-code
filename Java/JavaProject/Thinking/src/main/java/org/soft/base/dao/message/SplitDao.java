package org.soft.base.dao.message;

public interface SplitDao {

    /**
     * 求总页数
     * @param rows
     * @return
     */
    public int allPage(int rows  , int size);

    public int rowBegin(int currentPage , int size);
}

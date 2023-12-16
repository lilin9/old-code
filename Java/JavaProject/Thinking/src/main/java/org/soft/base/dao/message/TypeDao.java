package org.soft.base.dao.message;

import org.soft.base.model.Type;

import java.util.List;

public interface TypeDao {
    /**
     * 创建文章类型
     * @param type
     * @return
     */
    public boolean typeIssue(Type type);

    /**
     * 查询你所有类型
     * @return
     */
    public List<Type> typeList();

    /**
     * 删除类型
     * @param typeId
     * @return
     */
    public boolean typeDelById(int typeId);

    /**
     * 添加类型
     * @param typeId
     * @return
     */
    public Type typeById(int typeId);
}

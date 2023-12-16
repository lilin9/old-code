package org.soft.base.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.soft.base.model.Type;

import java.util.List;

@Mapper
public interface TypeDaoMapper {
    /**
     * 创建文章类型
     * @param type
     * @return
     */
    @Insert("insert into type(typeName) value (#{typeName})")
    public boolean typeIssue(Type type);

    /**
     * 查询你所有类型
     * @return
     */
    @Select("select * from type")
    public List<Type> typeList();

    /**
     * 删除类型
     * @param typeId
     * @return
     */
    @Delete("delete from type where typeId = #{typeId}")
    public boolean typeDelById(int typeId);

    /**
     * 添加类型
     * @param typeId
     * @return
     */
    @Select("select * from type where typeId = #{typeId}")
    public Type typeById(int typeId);
}

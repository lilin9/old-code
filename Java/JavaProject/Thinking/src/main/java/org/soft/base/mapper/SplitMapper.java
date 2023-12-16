package org.soft.base.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.soft.base.mapper.dynamic.SplitUtils;

import java.util.Map;

@Mapper
public interface SplitMapper {
//    @Select("select count(*) from ？")
    //动态SQL余军
    @SelectProvider(type = SplitUtils.class , method = "getTableRows")
    public int getArticleRows(Map<String,Object> map);
}

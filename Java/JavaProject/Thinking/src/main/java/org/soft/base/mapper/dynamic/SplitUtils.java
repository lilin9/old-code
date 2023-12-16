package org.soft.base.mapper.dynamic;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;
import java.util.Map;

public class SplitUtils {

    public String getTableRows(Map<String,Object> map){
        String sql = new SQL(){
            {
                SELECT("count(*)");
                FROM(map.get("tableName").toString());
                if(map.get("humanId") != null){
                    WHERE("humanId = #{humanId}");
                }else if (map.get("typeId") != null){
                    WHERE("typeId = #{typeId}");
                }else if(map.get("articleId") != null){
                    WHERE("articleId = #{articleId}");
                }
            }
        }.toString();
        return sql;
    }

    public static void main(String[] args) {
        SplitUtils s = new SplitUtils();
        Map<String , Object> map = new HashMap<>();
        map.put("tableName" , "article");
        map.put("humanId" , "humanId");
        String sql = s.getTableRows(map);
        System.out.println("sql ==== " + sql);
    }
}

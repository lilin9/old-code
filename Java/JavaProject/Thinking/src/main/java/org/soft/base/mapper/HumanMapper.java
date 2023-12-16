package org.soft.base.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.soft.base.model.Human;

@Mapper
public interface HumanMapper {

    @Insert("insert into human (humanName,humanNiceName,humanPassword,humanEmail) values (#{humanName},#{humanNiceName},#{humanPassword},#{humanEmail})")
    public boolean humanRegisterMapper(Human human);

    @Select("select * from human where humanName = #{humanName} and humanPassword =#{humanPassword}")
    public Human humanLoginMapper(Human human);

    @Update("update human set humanPassword = #{humanPassword} where humanId = #{humanId}")
    public boolean humanUpdateMapper(Human human);

    @Select("select * from human where humanId = #{humanId}")
    public Human humanById(int humanId);

}

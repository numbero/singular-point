package org.example.singularpoint.infra.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.singularpoint.infra.model.UserDO;

@Mapper
public interface UserMapper {

    int insert(UserDO userDO);

    UserDO selectById(@Param("id") Long id);

    @Select("select * from user where name = #{name}")
    UserDO selectByName(@Param("name") String name);
}

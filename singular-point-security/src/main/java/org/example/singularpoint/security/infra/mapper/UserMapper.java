package org.example.singularpoint.security.infra.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.singularpoint.security.infra.model.UserDO;

@Mapper
public interface UserMapper {

    @Insert("insert into security_user" +
            "(username, password, roles) " +
            "values(#{username}, #{password}, #{roles}) " +
            "on duplicate key update " +
            "password=values(password)" +
            ", roles=values(roles)")
    int insert(UserDO userDO);

    @Select("select * from security_user where username = #{username}")
    UserDO selectByUsername(String username);

    @Delete("delete from security_user where 1=1")
    int deleteAll();

}

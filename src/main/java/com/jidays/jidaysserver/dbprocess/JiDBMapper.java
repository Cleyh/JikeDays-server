package com.jidays.jidaysserver.dbprocess;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JiDBMapper {
    @Select("select hello from helloworld")
    public String hello();

    @Select("select world from helloworld")
    public String world();
}

package com.jidays.jidaysserver.dbprocess;

import org.apache.ibatis.annotations.Select;

public class JiDBController{
    @Select("select hello from HelloWorld")
    public static String hello() {
        return null;
    }

    @Select("select world from HelloWorld")
    public static String world() {
        return null;
    }
}

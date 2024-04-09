package com.jidays.jidaysserver.dbprocess;

import com.jidays.jidaysserver.entity.Subsource;
import com.jidays.jidaysserver.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JiDBMapper {
    @Select("select hello from helloworld")
    public List<String> hello();

    @Select("select world from helloworld")
    public List<String> world();

    @Select("SELECT sid, tittle, content FROM subsources LIMIT #{size} OFFSET #{offset}")
    List<Subsource> getSubsource(int offset, int size);

    @Insert("INSERT INTO `subsources` (`tittle`, `content`, `script`) VALUES (#{tittle}, #{content}, NULL)")
    boolean addSubsource(String tittle, String content);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User findUserByEmail(String email);
    @Insert("INSERT INTO user (name, email, password) VALUES (#{name}, #{email}, #{password})")
    boolean insertUser(User user);
}

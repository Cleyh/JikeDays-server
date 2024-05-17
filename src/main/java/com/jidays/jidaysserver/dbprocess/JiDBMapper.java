package com.jidays.jidaysserver.dbprocess;

import com.jidays.jidaysserver.entity.Subsource;
import com.jidays.jidaysserver.entity.Tweet;
import com.jidays.jidaysserver.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JiDBMapper {
    @Select("select hello from helloworld")
    public List<String> hello();

    @Select("select world from helloworld")
    public List<String> world();

    @Select("SELECT * FROM subsources LIMIT #{size} OFFSET #{offset}")
    List<Subsource> getSubsource(int offset, int size);

    @Insert("INSERT INTO `subsources` (`tittle`, `content`, `script`) VALUES (#{tittle}, #{content}, NULL)")
    boolean addSubsource(String tittle, String content);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User findUserByEmail(String email);
    @Insert("INSERT INTO user (name, email, password) VALUES (#{name}, #{email}, #{password})")
    boolean insertUser(User user);

    @Select("")
    Subsource getSubsourceFromID(int id);

    @Select("")
    List<Subsource> getUserSubscribeList(String email);

    @Insert("")
    boolean userSubsribe(String email, int subscribeID);

    @Delete("")
    void userUnsubsribe(String email, int subscribeID);

    @Select("SELECT * FROM tweets LIMIT #{size} OFFSET #{offset}")
    List<Tweet> getLatestTweetsTest(int offset, int size);

    @Select("SELECT * FROM subsources")
    List<Subsource> getAllSubsource();

    @Select("SELECT * FROM subsources WHERE type = #{type}")
    List<Subsource> getAllTypeSubsource(String type);

    @Insert("INSERT INTO tweets " +
            "(sid, title, summary, content, type, timeSlotA, timeSlotB, timeSlotC) VALUES " +
            "(#{sid}, #{title}, #{summary}, #{processedData}, #{type}, #{timeSlotA}, #{timeSlotB}, #{timeSlotC})")
    void addTweet(int sid, String title, String summary, String processedData, String type,
                  String timeSlotA, String timeSlotB, String timeSlotC);
}

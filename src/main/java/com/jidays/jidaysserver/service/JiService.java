package com.jidays.jidaysserver.service;

import com.jidays.jidaysserver.dbprocess.JiDBMapper;
import com.jidays.jidaysserver.entity.Subsource;
import com.jidays.jidaysserver.entity.User;
import com.jidays.jidaysserver.entity.UserLoginDto;
import com.jidays.jidaysserver.entity.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JiService {
    @Autowired
    private JiDBMapper jiDBMapper;

    public List<String> helloWorld() {
        return jiDBMapper.world();
    }

    // 获取所有订阅源信息（分页）
    public List<Subsource> getSubsource(int page, int size) {
        // 计算分页的offset
        int offset = page * size;
        return jiDBMapper.getSubsource(offset, size);
    }

    // 新增订阅源
    public boolean addSubsource(String tittle, String content) {
        jiDBMapper.addSubsource(tittle, content);
        return true;
    }

    // 注册用户
    public boolean registerUser(UserRegistrationDto registrationDto) {
        if (registrationDto.getEmail() == null || registrationDto.getUsername() == null || registrationDto.getPassword() == null) {
            return false;
        }

        if (jiDBMapper.findUserByEmail(registrationDto.getEmail()) != null) {
            return false;
        }

        User newUser = new User();
        newUser.setEmail(registrationDto.getEmail());
        newUser.setName(registrationDto.getUsername());
        newUser.setPassword(registrationDto.getPassword());

        jiDBMapper.insertUser(newUser);
        return true;
    }

    // 登陆
    public String login(UserLoginDto userLoginDto) {
        User getUser = jiDBMapper.findUserByEmail(userLoginDto.getEmail());
        if (getUser == null || !Objects.equals(getUser.getPassword(), userLoginDto.getPassword())) {
            return null;
        }
        return TokenService.generateToken(getUser.getEmail());
    }

    // 获取个人资料
    public User getProfile(String authHeader, String email) {
        if (!TokenService.isValidToken(authHeader, email)) {
            return null; // Token无效或过期
        }
        User result = jiDBMapper.findUserByEmail(email);
        result.setPassword("");
        return result;
    }

    // 返回指定的订阅信息
    public List<Subsource> getSubsourceFromList(List<Integer> get_list) {
        List<Subsource> result = new ArrayList<>();
        for (int i : get_list) {
            result.add(jiDBMapper.getSubsourceFromID(i));
        }
        return result;
    }


    public boolean getUserSubscribeList(String authHeader, String email, List<Subsource> result) {
        if (!TokenService.isValidToken(authHeader, email)) {
            return false;
        }
        result = jiDBMapper.getUserSubscribeList(email);
        return true;
    }

    public boolean subscribe(String email, int subscribeID) {
        List<Subsource> user_list = jiDBMapper.getUserSubscribeList(email);
        Subsource subsource = jiDBMapper.getSubsourceFromID(subscribeID);
        if (subsource == null) return false;
        if (user_list.contains(subsource)) return false;
        jiDBMapper.userSubsribe(email, subscribeID);
        return true;
    }

    public boolean unsubscribe(String email, int subscribeID) {
        List<Subsource> user_list = jiDBMapper.getUserSubscribeList(email);
        Subsource subsource = jiDBMapper.getSubsourceFromID(subscribeID);
        if (subsource == null) return false;
        if (!user_list.contains(subsource)) return false;
        jiDBMapper.userUnsubsribe(email, subscribeID);
        return false;
    }
}

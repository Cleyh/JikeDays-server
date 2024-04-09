package com.jidays.jidaysserver.service;

import com.jidays.jidaysserver.dbprocess.JiDBMapper;
import com.jidays.jidaysserver.entity.Subsource;
import com.jidays.jidaysserver.entity.User;
import com.jidays.jidaysserver.entity.UserLoginDto;
import com.jidays.jidaysserver.entity.UserRegistrationDto;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class JiService {
    @Autowired
    private JiDBMapper jiDBMapper;

    public List<String> helloWorld() {
        return jiDBMapper.world();
    }

    public List<Subsource> getSubsource(int page, int size) {
        // 计算分页的offset
        int offset = page * size;
        return jiDBMapper.getSubsource(offset, size);
    }

    public boolean addSubsource(String tittle, String content) {
        jiDBMapper.addSubsource(tittle, content);
        return true;
    }

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

    public String generateToken(User user) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .signWith(key) // 你需要替换为你自己的密钥
                .compact();
    }

    public String login(UserLoginDto userLoginDto) {
        User getUser = jiDBMapper.findUserByEmail(userLoginDto.getEmail());
        if (getUser == null || !Objects.equals(getUser.getPassword(), userLoginDto.getPassword())){
            return null;
        }
        return TokenService.generateToken(getUser.getEmail());
    }

    public User getProfile(String authHeader, String email) {
        if (!TokenService.isValidToken(authHeader)) {
            return null; // Token无效或过期
        }
        User result = jiDBMapper.findUserByEmail(email);
        result.setPassword("");
        return result;
    }


}

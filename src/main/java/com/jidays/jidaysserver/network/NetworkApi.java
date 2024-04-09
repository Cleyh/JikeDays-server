package com.jidays.jidaysserver.network;

import com.jidays.jidaysserver.entity.Subsource;
import com.jidays.jidaysserver.entity.User;
import com.jidays.jidaysserver.entity.UserLoginDto;
import com.jidays.jidaysserver.entity.UserRegistrationDto;
import com.jidays.jidaysserver.service.JiService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NetworkApi {
    @Autowired
    private JiService jiService;

    @RequestMapping(path = "/helloworld", method = RequestMethod.GET)
    public List<String> get1() {
        return jiService.helloWorld();
    }

    @GetMapping("/getSubsource")
    public List<Subsource> getSubsource(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        return jiService.getSubsource(page, size);
    }

    @PostMapping("/addSubsource")
    public boolean addSubsource(@RequestParam(name = "tittle", defaultValue = "无标题的订阅源") String tittle,
                                @RequestParam(name = "content", defaultValue = "空的简介") String content) {
        return jiService.addSubsource(tittle, content);
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        boolean result = jiService.registerUser(registrationDto);
        if (result) {
            return ResponseEntity.ok().body("用户注册成功");
        } else {
            return ResponseEntity.badRequest().body("注册失败，可能是用户名已存在");
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        String token = jiService.login(userLoginDto);
        if (token != null) {
            return ResponseEntity.ok().body(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }
    }

    @GetMapping("/user/getProfile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authHeader, @RequestParam(name = "user", defaultValue = "") String email) {
        User user = jiService.getProfile(authHeader, email);
        if (user != null) {
            return ResponseEntity.ok(user); // 或者仅返回需要的用户信息字段
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("无效的Token或找不到用户");
        }
    }

    @GetMapping("/getSubscribe")
    public ResponseEntity<?> getSubscribe(@RequestHeader("Authorization") String authHeader,
                                          @RequestParam(value = "user", defaultValue = "") String email,
                                          @RequestParam(value = "subscribe", defaultValue = "-1") int sid)
    {

        return null;
    }
}

package com.jidays.jidaysserver.network;

import com.jidays.jidaysserver.entity.Subsource;
import com.jidays.jidaysserver.entity.User;
import com.jidays.jidaysserver.entity.UserLoginDto;
import com.jidays.jidaysserver.entity.UserRegistrationDto;
import com.jidays.jidaysserver.service.JiService;
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
    public ResponseEntity<ApiResponse> getSubsource(@RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        List<Subsource> subsources = jiService.getSubsource(page, size);
        return ResponseEntity.ok(ApiResponse.success("订阅源获取成功", subsources));
    }

    @PostMapping("/addSubsource")
    public ResponseEntity<ApiResponse> addSubsource(@RequestParam(name = "tittle", defaultValue = "无标题的订阅源") String tittle,
                                                    @RequestParam(name = "content", defaultValue = "空的简介") String content) {
        boolean result = jiService.addSubsource(tittle, content);
        if (result) {
            return ResponseEntity.ok(ApiResponse.success("订阅源添加成功", null));
        } else {
            // 根据实际情况，这里的错误信息和错误对象可以进一步细化
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("订阅源添加失败", null));
        }
    }

    @PostMapping("/user/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        boolean result = jiService.registerUser(registrationDto);
        if (result) {
            return ResponseEntity.ok(ApiResponse.success("用户注册成功", null));
        } else {
            return ResponseEntity.badRequest().body(ApiResponse.error("注册失败，可能是用户名已存在", null));
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserLoginDto userLoginDto) {
        String token = jiService.login(userLoginDto);
        if (token != null) {
            return ResponseEntity.ok(ApiResponse.success("登陆成功", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("用户名或密码错误", null));
        }
    }

    @GetMapping("/user/getProfile")
    public ResponseEntity<ApiResponse> getProfile(@RequestHeader("Authorization") String authHeader, @RequestParam(name = "user", defaultValue = "") String email) {
        User user = jiService.getProfile(authHeader, email);
        if (user != null) {
            return ResponseEntity.ok(ApiResponse.success("获取用户信息成功", user));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("无效的Token或找不到用户", null));
        }
    }

    @GetMapping("/getSubscribe")
    public ResponseEntity<ApiResponse> getSubscribe(@RequestHeader("Authorization") String authHeader,
                                          @RequestParam(value = "user", defaultValue = "") String email,
                                          @RequestParam(value = "subscribe", defaultValue = "-1") int sid) {

        return null;
    }
}

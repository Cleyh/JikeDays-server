package com.jidays.jidaysserver.network;

import com.jidays.jidaysserver.entity.*;
import com.jidays.jidaysserver.service.JiService;
import com.jidays.jidaysserver.service.TokenService;
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
    public ResponseEntity<ApiResponse> get1() {
        List<String> result = jiService.helloWorld();
        return ResponseEntity.ok(ApiResponse.success("Hello!", result));
    }

    // 测试
    @GetMapping("/Test")
    public ResponseEntity<ApiResponse> getTest() {
        jiService.getTest();
        return ResponseEntity.ok(ApiResponse.success("Hello!", null));
    }

    // 获取所有订阅源列表页&表
    @GetMapping("/getSubsource")
    public ResponseEntity<ApiResponse> getSubsource(@RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        List<Subsource> subsources = jiService.getSubsource(page, size);
        return ResponseEntity.ok(ApiResponse.success("订阅源获取成功", subsources));
    }

    // 添加订阅源
    @PostMapping("/addSubsource")
    public ResponseEntity<ApiResponse> addSubsource(@RequestBody Subsource subsource,
                                                    @RequestPart("subfile") SubFile subfile) {
        boolean result = jiService.addSubsource(subsource.getTittle(), subsource.getContent());
        if (result) {
            return ResponseEntity.ok(ApiResponse.success("订阅源添加成功", null));
        } else {
            // 根据实际情况，这里的错误信息和错误对象可以进一步细化
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("订阅源添加失败", null));
        }
    }

    @PostMapping("/user/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        boolean result = jiService.registerUser(registrationDto);
        if (result) {
            return ResponseEntity.ok(ApiResponse.success("用户注册成功", null));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("注册失败，可能是用户名已存在", null));
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserLoginDto userLoginDto) {
        String token = jiService.login(userLoginDto);
        if (token != null) {
            return ResponseEntity.ok(ApiResponse.success("登陆成功", token));
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("用户名或密码错误", null));
        }
    }

    @GetMapping("/user/getProfile")
    public ResponseEntity<ApiResponse> getProfile(@RequestHeader("Authorization") String authHeader,
                                                  @RequestParam(name = "user", defaultValue = "") String email) {
        User user = jiService.getProfile(authHeader, email);
        if (user != null) {
            return ResponseEntity.ok(ApiResponse.success("获取用户信息成功", user));
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("无效的Token或找不到用户", null));
        }
    }

    // 获取用户的订阅列表
    @GetMapping("/getSubsourceFromList")
    public ResponseEntity<ApiResponse> getSubsourceFromList(@RequestBody() List<Integer> sub_list) {
        List<Subsource> result = jiService.getSubsourceFromList(sub_list);
        ;

        return ResponseEntity.ok(ApiResponse.success("获取订阅源成功", result));
    }

    // 返回云端的用户订阅列表
    @GetMapping("/getUserSubscribeList")
    public ResponseEntity<ApiResponse> getUserSubscribeList(@RequestHeader("Authorization") String authHeader,
                                                            @RequestParam(name = "user") String email) {
        List<Subsource> result = null;
        if (!jiService.getUserSubscribeList(authHeader, email, result)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("无效token或找不到用户", null));
        }
        return ResponseEntity.ok(ApiResponse.success("获取用户订阅列表成功", result));
    }

    // 订阅
    @PostMapping("/subscribe")
    public ResponseEntity<ApiResponse> subscribe(@RequestHeader("Authorization") String authHeader,
                                                 @RequestParam(name = "user") String email,
                                                 @RequestParam(name = "subscribe") int subscribeID){
        if(!TokenService.isValidToken(authHeader,email)){
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("无效token或找不到用户",null));
        }

        if(!jiService.subscribe(email,subscribeID)){
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("订阅失败，可能订阅ID不存在",null));
        }

        return ResponseEntity.ok(ApiResponse.success("订阅成功", null));
    }

    // 取消订阅
    @PostMapping("/unsubscribe")
    public ResponseEntity<ApiResponse> unsubscribe(@RequestHeader("Authorization") String authHeader,
                                                 @RequestParam(name = "user") String email,
                                                 @RequestParam(name = "subscribe") int subscribeID){
        if(!TokenService.isValidToken(authHeader,email)){
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("无效token或找不到用户",null));
        }

        if(!jiService.unsubscribe(email,subscribeID)){
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("取消订阅失败，可能订阅ID不存在",null));
        }

        return ResponseEntity.ok(ApiResponse.success("取消订阅成功", null));
    }

    @GetMapping("/getLatestTweets")
    public ResponseEntity<ApiResponse> getLatestTweets(@RequestParam(name = "user") String email,
                                                       @RequestParam(name = "page", defaultValue = "1") int page,
                                                       @RequestParam(name = "size", defaultValue = "10") int size) {
        List<Tweet> result = jiService.getLatestTweets(email, page, size);
        return ResponseEntity.ok(ApiResponse.success("获取最新推文成功", result));
   }
}

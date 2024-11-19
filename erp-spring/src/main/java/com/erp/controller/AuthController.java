package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.model.User;
import com.erp.server.UserServer;
import com.erp.utils.JsonResponse;
// 用户认证控制器

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserServer userServer; // 注入UserServer

    // 登录接口
    @PostMapping("/login")
    public JsonResponse login(@RequestBody LoginRequest request) {
        User user = userServer.findByUsername(request.username);
        if (user != null && user.getPassword().equals(request.password)) {
            return new JsonResponse(true, "登录成功"); // 返回成功消息
        } else {
            return new JsonResponse(false, "登录失败"); // 返回失败消息
        }
    }

    // 注册接口
    @PostMapping("/register")
    public JsonResponse register(@RequestBody RegisterRequest request) {
        User user = userServer.findByUsername(request.username);
        if (!request.password.equals(request.repassword)) {
            return new JsonResponse(false, "两次密码不一致"); // 返回失败消息
        }
        if (user == null) {
            user = userServer.saveUser(request.username, request.password);
            if (user != null) {
                return new JsonResponse(true, "注册成功"); // 返回成功消息
            } else {
                return new JsonResponse(false, "注册失败"); // 返回失败消息
            }
        } else {
            return new JsonResponse(false, "用户名已存在"); // 返回失败消息
        }
    }

    public static class LoginRequest {

        public String username;
        public String password;
    }

    public static class RegisterRequest {

        public String username;
        public String password;
        public String repassword;
    }
}

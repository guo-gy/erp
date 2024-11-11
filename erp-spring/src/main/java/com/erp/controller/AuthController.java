package com.erp.controller;

import org.springframework.web.bind.annotation.*;

// 用户认证控制器
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // 登录接口
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        // 这里可以添加登录逻辑，例如验证用户名和密码
        if ("admin".equals(request.username) && "password".equals(request.password)) {
            return "登录成功"; // 返回成功消息
        } else {
            return "登录失败：用户名或密码错误"; // 返回失败消息
        }
    }

    // 注册接口
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        // 这里可以添加注册逻辑，例如保存用户信息到数据库
        // 假设注册成功
        return "注册成功"; // 返回成功消息
    }

    // 登录请求体
    public static class LoginRequest {
        public String username; // 用户名
        public String password; // 密码
    }

    // 注册请求体
    public static class RegisterRequest {
        public String username; // 用户名
        public String password; // 密码
        public String email;    // 邮箱
    }
} 
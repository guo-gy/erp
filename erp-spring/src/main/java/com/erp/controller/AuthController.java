package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.model.Company;
import com.erp.model.User;
import com.erp.service.UserServer;
import com.erp.utils.JsonResponse;
import com.erp.service.CompanyService;
// 用户认证控制器

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserServer userServer; // 注入UserServer

    @Autowired
    private CompanyService companyService; // 注入CompanyService

    // 登录接口
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = userServer.getUserByUsername(request.username);
        if (user != null && user.getPassword().equals(request.password)) {
            return new LoginResponse(true, "登录成功", user); // 返回成功消息
        } else {
            return new LoginResponse(false, "登录失败", null); // 返回失败消息
        }
    }

    // 注册接口
    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        Company company = companyService.getCompanyByName(request.companyname);
        User user = userServer.getUserByUsername(request.username);
        if (!request.password.equals(request.repassword)) {
            return new RegisterResponse(false, "两次密码不一致", null); // 返回失败消息
        }
        if (user != null) {
            return new RegisterResponse(false, "用户名已存在", null); // 返回失败消息
        }
        if (company != null) {
            return new RegisterResponse(false, "公司名已存在", null); // 返回失败消息
        }
        user = userServer.saveUser(request.username, request.password);
        company = companyService.saveCompany(request.companyname, user);
        if (user != null) {
            return new RegisterResponse(true, "注册成功", user); // 返回成功消息
        } else {
            return new RegisterResponse(false, "注册失败", null); // 返回失败消息
        }
    }

    public static class LoginRequest {

        public String username;
        public String password;
    }

    public static class RegisterRequest {
        public String companyname;
        public String username;
        public String password;
        public String repassword;
    }

    public static class LoginResponse extends JsonResponse {
        User user;

        public LoginResponse(boolean success, String message, User user) {
            super(success, message);
            this.user = user;
        }
    }

    public static class RegisterResponse extends JsonResponse {
        User user;

        public RegisterResponse(boolean success, String message, User user) {
            super(success, message);
            this.user = user;
        }
    }
}

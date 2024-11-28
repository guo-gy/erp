package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.service.UserService;
import com.erp.entity.Company;
import com.erp.entity.User;
import com.erp.service.CompanyService;
import com.erp.utils.JsonResponse;
import java.util.List;
// 用户认证控制器

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService; // 注入UserService

    @Autowired
    private CompanyService companyService; // 注入CompanyService

    // 登录接口
    @PostMapping("/login")
    public JsonResponse<Integer> login(@RequestBody LoginRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "登录失败", null);
        User user = userService.getUserByName(request.userName);
        if (request.userName == null || request.userName.equals("")) {
            response.message = "用户名不能为空";
        } else if (request.password == null || request.password.equals("")) {
            response.message = "密码不能为空";
        } else if (user == null) {
            response.message = "用户不存在";
        } else if (!user.getPassword().equals(request.password)) {
            response.message = "密码错误";
        } else {
            response.success = true;
            response.message = "登录成功";
            response.data = user.getId();
        }
        return response; // 返回消息
    }

    // 注册接口
    @PostMapping("/register")
    public JsonResponse<Integer> register(@RequestBody RegisterRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "注册失败", null);
        User user = userService.getUserByName(request.userName);
        Company company = companyService.getCompanyByName(request.companyName);
        if (request.companyName == null || request.companyName.equals("")) {
            response.message = "公司名不能为空";
        } else if (request.userName == null || request.userName.equals("")) {
            response.message = "用户名不能为空";
        } else if (request.password == null || request.password.equals("")) {
            response.message = "密码不能为空";
        } else if (request.repassword == null || request.repassword.equals("")) {
            response.message = "确认密码不能为空";
        } else if (company != null) {
            response.message = "公司名已存在";
        } else if (user != null) {
            response.message = "用户名已存在";
        } else if (!request.password.equals(request.repassword)) {
            response.message = "两次密码不一致";
        } else {
            company = companyService.addCompany(request.companyName);
            user = userService.addUser(request.userName, request.password, company.getId());
            response.success = true;
            response.message = "注册成功";
            response.data = user.getId();
        }
        return response; // 返回消息
    }

    // 注册接口
    @PostMapping("/registerworker")
    public JsonResponse<Integer> registerworker(@RequestBody RegisterRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "注册失败", null);
        User user = userService.getUserByName(request.userName);
        Company company = companyService.getCompanyByName(request.companyName);
        if (request.userName == null || request.userName.equals("")) {
            response.message = "用户名不能为空";
        } else if (request.password == null || request.password.equals("")) {
            response.message = "密码不能为空";
        } else if (request.repassword == null || request.repassword.equals("")) {
            response.message = "确认密码不能为空";
        } else if (user != null) {
            response.message = "用户名已存在";
        } else if (!request.password.equals(request.repassword)) {
            response.message = "两次密码不一致";
        } else {
            user = userService.addUser(request.userName, request.password, company.getId());
            response.success = true;
            response.message = "注册成功";
            response.data = user.getId();
        }
        return response; // 返回消息
    }

    // 获取用户名接口
    @GetMapping("/{userId}/name")
    public JsonResponse<String> getUserName(@PathVariable("userId") Integer userId) {
        System.out.println("Received request for userId: " + userId); // 添加日志
        JsonResponse<String> response = new JsonResponse<String>(false, "获取失败", null);
        try {
            User user = userService.getUserById(userId);
            response.success = true;
            response.message = "获取用户名成功";
            response.data = user.getName();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    // 获取用户名接口
    @GetMapping("/{userId}/description")
    public JsonResponse<String> getUserDescription(@PathVariable("userId") Integer userId) {
        JsonResponse<String> response = new JsonResponse<String>(false, "获取失败", null);
        try {
            User user = userService.getUserById(userId);
            response.success = true;
            response.message = "获取用户名成功";
            response.data = user.getDescription();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @PostMapping("/upduser")
    public JsonResponse<Integer> updUser(@RequestBody updUserRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "更新失败", null);
        try {
            userService.updUser(request.userId, request.name, request.description);
            response.success = true;
            response.message = "更新用户成功";
            response.data = request.userId;
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    // 获取公司id接口
    @GetMapping("/{userId}/companyid")
    public JsonResponse<Integer> getCompanyId(@PathVariable("userId") Integer userId) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "获取失败", null);
        try {
            User user = userService.getUserById(userId);
            response.success = true;
            response.message = "获取公司id成功";
            response.data = user.getCompanyId();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    // 获取公司id接口
    @GetMapping("/{company}/user")
    public JsonResponse<List<User>> getCompanyUser(@PathVariable("companyId") Integer companyId) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "获取失败", null);
        try {
            User user = userService.getUserByCompanyId(companyId);
            response.success = true;
            response.message = "获取公司id成功";
            response.data = user.getCompanyId();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    public static class LoginRequest {

        public String userName;
        public String password;
    }

    public static class RegisterRequest {
        public String companyName;
        public String userName;
        public String password;
        public String repassword;
    }

    public static class updUserRequest {
        public Integer userId;
        public String name;
        public String description;
    }
}

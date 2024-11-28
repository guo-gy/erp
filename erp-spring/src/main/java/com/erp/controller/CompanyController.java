package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.erp.entity.Company;
import com.erp.service.CompanyService;

import com.erp.utils.JsonResponse;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService; // 注入CompanyService

    @GetMapping("/{companyId}/name")
    public JsonResponse<String> getCompanyName(@PathVariable("companyId") Integer companyId) {
        JsonResponse<String> response = new JsonResponse<String>(false, "获取失败", null);
        try {
            Company company = companyService.getCompanyById(companyId);
            response.success = true;
            response.message = "获取公司名成功";
            response.data = company.getName();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @GetMapping("/{companyId}/money")
    public JsonResponse<Double> getCompanyMoney(@PathVariable("companyId") Integer companyId) {
        JsonResponse<Double> response = new JsonResponse<Double>(false, "获取失败", null);
        try {
            Company company = companyService.getCompanyById(companyId);
            response.success = true;
            response.message = "获取公司资金量成功";
            response.data = company.getMoney();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @GetMapping("/{companyId}/description")
    public JsonResponse<String> getCompanyDescription(@PathVariable("companyId") Integer companyId) {
        JsonResponse<String> response = new JsonResponse<String>(false, "获取失败", null);
        try {
            Company company = companyService.getCompanyById(companyId);
            response.success = true;
            response.message = "获取公司简介成功";
            response.data = company.getDescription();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @RequestMapping("/updcompany")
    public JsonResponse<Integer> updCompany(@RequestBody updCompanyRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "更新失败", null);
        try {
            companyService.updCompany(request.id, request.name, request.description);
            response.success = true;
            response.message = "更新公司信息成功";
            response.data = request.id;
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    public static class updCompanyRequest {
        public Integer id;
        public String name;
        public String description;
    }

}

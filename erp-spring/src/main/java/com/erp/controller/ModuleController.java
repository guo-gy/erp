package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.erp.entity.module;
import com.erp.service.ModuleService;

import com.erp.utils.JsonResponse;
import java.util.List;

@RestController
@RequestMapping("/api/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService; // 注入CompanyService

    @GetMapping("/module")
    public JsonResponse<List<module>> getAllmodule() {
        JsonResponse<List<module>> response = new JsonResponse<List<module>>(false, "获取失败", null);
        try {
            List<module> module = moduleService.getAllmodules();
            response.success = true;
            response.message = "获取所有权限成功";
            response.data = module;
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @GetMapping("/{moduleId}/name")
    public JsonResponse<String> getmoduleName(@PathVariable("moduleId") Integer moduleId){
        JsonResponse<String> response = new JsonResponse<String>(false, "获取失败", null);
        try {
            module module = moduleService.getmoduleById(moduleId);
            response.success = true;
            response.message = "获取权限名成功";
            response.data = module.getName();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

}

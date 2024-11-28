package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.erp.entity.Model;
import com.erp.service.ModelService;

import com.erp.utils.JsonResponse;
import java.util.List;

@RestController
@RequestMapping("/api/model")
public class ModelController {

    @Autowired
    private ModelService modelService; // 注入CompanyService

    @GetMapping("/model")
    public JsonResponse<List<Model>> getAllModel() {
        JsonResponse<List<Model>> response = new JsonResponse<List<Model>>(false, "获取失败", null);
        try {
            List<Model> model = modelService.getAllmodels();
            response.success = true;
            response.message = "获取所有权限成功";
            response.data = model;
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @GetMapping("/{modelId}/modelname")
    public JsonResponse<String> getModelName(@PathVariable("modelId") Integer modelId){
        JsonResponse<String> response = new JsonResponse<String>(false, "获取失败", null);
        try {
            Model model = modelService.getModelById(modelId);
            response.success = true;
            response.message = "获取权限名成功";
            response.data = model.getName();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

}

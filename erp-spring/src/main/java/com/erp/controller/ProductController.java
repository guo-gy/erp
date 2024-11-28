package com.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.entity.Product;
import com.erp.service.ProductService;
import com.erp.utils.JsonResponse;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getall")
    public JsonResponse<List<Product>> getAllProducts() {
        JsonResponse<List<Product>> response = new JsonResponse<List<Product>>(false, "", null);
        try {
            response.success = true;
            response.message = "获取成功";
            response.data = productService.getAllProducts();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @PostMapping("/addinventory")
    public JsonResponse<Integer> createProduct(@RequestBody CreateRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "", null);
        Product product = productService.getProductByName(request.name);
        if (request.name == null || request.name.equals("")) {
            response.message = "产品名不能为空";
        } else if (productService.getProductByName(request.name) != null) {
            response.message = "产品名已存在";
        } else {
            product = productService.addProduct(request.name);
            response.success = true;
            response.message = "创建成功";
            response.data = product.getId();
        }
        return response;
    }

    @GetMapping("/{id}")
    public JsonResponse<String> getProductName(@PathVariable("id") Integer id) {
        JsonResponse<String> response = new JsonResponse<String>(false, "", null);
        try {
            Product product = productService.getProductById(id);
            response.success = true;
            response.message = "获取成功";
            response.data = product.getName();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    public static class CreateRequest {
        public String name;
    }
}

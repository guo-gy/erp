package com.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.model.Product;
import com.erp.service.ProductSever;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductSever productSever;

    @GetMapping("/get")
    public List<Product> getAllProducts() {
        return productSever.getAllProducts();
    }

    @PostMapping("/create")
    public String createProduct(@RequestBody CreateRequest request) {
        if (request.name == null || request.price == null) {
            return "创建失败：产品名或价格为空";
        }
        productSever.createProduct(request.name, request.price);
        return "创建成功";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productSever.deleteProduct(id);
    }

    public static class CreateRequest {

        public String name;
        public Double price;
    }
}

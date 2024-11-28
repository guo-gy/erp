package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.Product;
import com.erp.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 增
    public Product addProduct(String name) {
        Product product = new Product();
        product.setName(name);
        productRepository.save(product);
        return product;
    }

    // 删
    public void delProduct(Integer id) {
        productRepository.deleteById(id);
    }

    // 改
    public void updProduct(Integer id, String name) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(name);
            productRepository.save(product);
        }
    }

    // 查
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

}

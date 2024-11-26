package com.erp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erp.model.Product;
import com.erp.repository.ProductRepository;

@Service
public class ProductSever {

    private final ProductRepository productRepository;

    public ProductSever(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(String name, Double price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

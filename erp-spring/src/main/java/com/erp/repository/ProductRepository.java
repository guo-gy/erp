package com.erp.repository;

import com.erp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 产品数据访问层接口
 * 继承JpaRepository以获得基本的CRUD操作功能
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * 继承的主要方法包括:
     * - findAll(): 查询所有产品
     * - findById(): 根据ID查询产品
     * - save(): 保存或更新产品
     * - deleteById(): 根据ID删除产品
     */
    
} 
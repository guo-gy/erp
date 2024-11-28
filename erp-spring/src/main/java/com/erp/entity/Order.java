package com.erp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 订单ID

    private Integer productId; // 产品ID
    private Integer originId; // 订单发起者
    private Integer targetId; // 订单接受者
    private Double price; // 价格
    private Integer quantity; // 数量
}

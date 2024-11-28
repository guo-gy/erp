package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.Order;
import com.erp.repository.OrderRepository;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // 增
    public Order addOrder(Integer productId, Integer originId, Integer targetId, Double price, Integer quantity) {
        Order order = new Order();
        order.setProductId(productId);
        order.setOriginId(originId);
        order.setTargetId(targetId);
        order.setPrice(price);
        order.setQuantity(quantity);
        orderRepository.save(order);
        return order;
    }

    // 删
    public void delOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    // 改

    // 查
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrderByOriginId(Integer originId) {
        return orderRepository.findByOriginId(originId);
    }

    public List<Order> getOrderByTargetId(Integer targetId) {
        return orderRepository.findByTargetId(targetId);
    }

}

package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> findByOriginId(Integer originId);
    public List<Order> findByTargetId(Integer targetId);
}

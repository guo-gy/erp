package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.Permission;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    public List<Permission> findByUserId(Integer userId);
}

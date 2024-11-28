package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>  {
    
}

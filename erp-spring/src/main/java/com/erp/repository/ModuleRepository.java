package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.module;

@Repository
public interface ModuleRepository extends JpaRepository<module, Integer>  {
    module findByName(String moduleName);
    
}

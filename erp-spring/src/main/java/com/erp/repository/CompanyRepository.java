package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByName(String name);
}

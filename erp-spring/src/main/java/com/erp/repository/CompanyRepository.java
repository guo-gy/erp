package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);
}

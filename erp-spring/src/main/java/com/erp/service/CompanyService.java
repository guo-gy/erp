package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.Company;
import com.erp.repository.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // 增
    public Company addCompany(String companyName) {
        Company company = new Company();
        company.setName(companyName);
        company.setMoney(100000.0);
        company.setDescription(null);
        companyRepository.save(company);
        return company;
    }

    // 删
    public void delCompany(Integer id) {
        companyRepository.deleteById(id);
    }

    // 改
    public Company updCompany(Integer id, String companyName, String description) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            company.setName(companyName);
            company.setDescription(description);
            companyRepository.save(company);
            return company;
        }
        return null;
    }

    public Company updCompany(Integer id, Double money) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            company.setMoney(money);
            return companyRepository.save(company);
        }
        return null;
    }

    // 查
    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company getCompanyByName(String name) {
        return companyRepository.findByName(name);
    }
}

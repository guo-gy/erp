package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erp.model.Company;
import com.erp.repository.CompanyRepository;
import com.erp.model.User;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Company saveCompany(String companyname, User Admin) {
        Company company = new Company();
        company.setName(companyname);
        company.setAdmin(Admin);
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company getCompanyByName(String name) {
        return companyRepository.findByName(name);
    }

    void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}

package com.erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import com.erp.model.Company;

/**
 * 用户实体类
 */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 用户ID

    private String username; // 用户名

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company; // 用户所属公司

    private String password; // 密码

    private Integer[] permissions; // 用户对各个模块的权限

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer[] getPermissions() {
        return permissions;
    }

    public void setAllPermissions() {
        this.permissions = new Integer[5];
        for (int i = 0; i < 5; i++) {
            this.permissions[i] = 1;
        }
    }

    public void setNullPermissions() {
        this.permissions = new Integer[5];
        for (int i = 0; i < 5; i++) {
            this.permissions[i] = -1;
        }
    }

    public void setPermissions(int model, int Permission) {
        this.permissions[model] = Permission;
    }

    public void setPermissions(Integer[] permissions) {
        this.permissions = permissions;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}

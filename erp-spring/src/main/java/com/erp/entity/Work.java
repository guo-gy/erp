package com.erp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "works")
public class Work {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 工作ID

    private String name; // 工作名称

    private Integer originId; // 工作发起者

    private Integer targetId; // 工作接受者
    private boolean status; // 工作状态

}

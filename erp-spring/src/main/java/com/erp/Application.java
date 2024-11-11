package com.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ERP系统启动类
 * 使用@SpringBootApplication注解标记为Spring Boot应用程序
 * 包含自动配置、组件扫描等功能
 */
@SpringBootApplication 
public class Application {
    /**
     * 应用程序入口方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
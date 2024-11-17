# ERP 系统

## 项目结构
- erp-vue: 前端代码
- erp-springboot: 后端代码

## 核心功能
1. 用户登录
2. 数据管理
   - 产品列表
   - 添加产品
3. 权限控制

## 技术栈
- 前端：Vue3 + Element Plus
- 后端：Spring Boot + MyBatis + MySQL  

## ero,启动！
1.后端：
```shell
cd erp-springboot//进入后端项目目录
mvn clean install//安装依赖
mvn spring-boot:run//启动项目
```
2.前端：
```shell
cd erp-vue//进入前端项目目录
npm install//安装依赖
npm run dev//启动项目
```

## 未来计划
- 添加更多功能
- 优化用户体验
- 增加错误处理和用户反馈
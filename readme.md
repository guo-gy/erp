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
- 前端：

1. **Vue.js**: 作为主要的前端框架，用于构建用户界面。
2. **Vue Router**: 用于实现前端路由管理，允许在不同的组件之间导航。
3. **Element Plus**: 一个基于 Vue 3 的 UI 组件库，提供了丰富的组件和样式。
4. **Axios**: 用于处理 HTTP 请求，方便与后端 API 进行交互。
5. **CSS**: 用于样式设计，项目中使用了 scoped CSS 来限制样式的作用范围。
此外，项目还使用了 Vue CLI 进行项目构建和管理。


- 后端：

1. **Spring Boot**：项目使用了Spring Boot作为基础框架，简化了配置和开发过程。
2. **Spring Data JPA**：用于简化数据库操作，提供了对JPA的支持。
3. **MySQL**：作为数据库，使用MySQL Connector/J驱动进行连接。
4. **RESTful API**：通过控制器（如`ProductController`和`AuthController`）实现RESTful风格的API。
5. **JSON**：使用JSON格式进行数据交换，`JsonResponse`类用于统一返回格式。
6. **CORS**：配置了跨域资源共享（CORS），允许特定的前端地址进行请求。
7. **Maven**：作为构建工具，管理项目依赖和构建过程。


## erp,启动！
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

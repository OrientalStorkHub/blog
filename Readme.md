# Blog Microservices Project

## 项目简介
这是一个基于 Spring Cloud 和 Nacos 构建的博客微服务项目，旨在实现用户认证、内容管理、文件和媒体管理、通知和消息、统计和分析等功能。

## 使用的技术栈
- Spring Boot
- Spring Cloud
- Nacos
- Maven
- mysql

## 微服务模块
本项目包含以下微服务：

1. **用户认证服务 (blog-user-auth-service)** - 负责用户的认证和授权。
2. **内容服务 (blog-content-service)** - 负责博客文章、评论和标签的管理。
3. **文件和媒体服务 (blog-file-media-service)** - 负责用户上传的文件和媒体资源管理。
4. **通知和消息服务 (blog-notification-messaging-service)** - 负责系统通知和用户消息管理。
5. **统计和分析服务 (blog-statistics-analytics-service)** - 负责博客内容的统计和分析。

## 项目结构
```
blog/
├── blog-user-auth-service/
├── blog-content-service/
├── blog-file-media-service/
├── blog-notification-messaging-service/
├── blog-statistics-analytics-service/
└── README.md
```
## 前置条件
- Java 17
- Maven 3.6+
- Nacos 2.x (用于服务发现和配置管理)

## 快速开始

### 1. 克隆项目
```bash
git clone https://github.com/OrientalStorkHub/blog.git
cd blog
```

### 2. 配置 Nacos
确保 Nacos 服务器正在运行，并配置如下环境变量：

`application.yml`
```yaml
spring:
  application:
    name: [service-name]
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
```

### 3. 构建和启动服务
用户认证服务
```bash
cd blog-user-auth-service
mvn clean install
mvn spring-boot:run
```
内容服务
```bash
cd blog-content-service
mvn clean install
mvn spring-boot:run
```
文件和媒体服务
```bash
cd blog-file-media-service
mvn clean install
mvn spring-boot:run
```
通知和消息服务
```bash
cd blog-notification-messaging-service
mvn clean install
mvn spring-boot:run
```
统计和分析服务
```bash
cd blog-statistics-analytics-service
mvn clean install
mvn spring-boot:run
```

## 准备实现的功能
- [ ] 用户注册和登录
- [ ] 博客文章的创建、编辑和删除
- [ ] 用户评论和回复功能
- [ ]文件和媒体的上传和管理
- [ ] 系统通知和用户消息推送
- [ ] 博客文章的统计和分析
- [ ] 用户角色和权限管理
- [ ] 基于标签的文章分类和搜索

## 贡献指南
欢迎贡献代码！请先 Fork 本项目，然后提交 Pull Request。







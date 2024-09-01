package com.orientalstorkhub.blog.userauthservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

// import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
// @MapperScan("com.orientalstorkhub.blog.userauthservice.repository")
@ComponentScan(basePackages = {"com.orientalstorkhub.blog.userauthservice", "com.orientalstorkhub.blog.common"}) // 用以扫描blog-common中的全局异常处理器的
public class UserAuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAuthServiceApplication.class, args);
    }
}
 
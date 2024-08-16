package com.orientalstorkhub.blog.gatewayservice;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;




@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.orientalstorkhub.blog.gatewayservice", "com.orientalstorkhub.blog.common"})
public class GatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

}

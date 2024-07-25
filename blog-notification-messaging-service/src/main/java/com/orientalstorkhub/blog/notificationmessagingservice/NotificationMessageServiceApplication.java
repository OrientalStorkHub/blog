package com.orientalstorkhub.blog.notificationmessagingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationMessageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationMessageServiceApplication.class, args);
    }
}

package com.orientalstorkhub.blog.statisticsanalyticsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StatisticsAnalyticsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsAnalyticsServiceApplication.class, args);
    }
}

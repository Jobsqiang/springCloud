package com.qiangjiang.fegin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.qiangjiang.fegin.client") 	//feign接口的地址
public class ApplicationFegin {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationFegin.class);
    }
}

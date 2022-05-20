package com.unicoin.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        //khai bao base package de autowire duoc cac class
        //vi du nhu RabbitMQMessageProducer trong package com.unicoin.amqp cua module amqp
        scanBasePackages = {
                "com.unicoin.product",
                "com.unicoin.amqp"
        }
)
@EnableEurekaClient
//link base package contains interface feign client
@EnableFeignClients(
        basePackages = "com.unicoin.clients"
)
public class ProductManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductManageApplication.class, args);
    }
}

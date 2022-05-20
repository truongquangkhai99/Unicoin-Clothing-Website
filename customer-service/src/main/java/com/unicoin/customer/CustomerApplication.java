package com.unicoin.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
        //khai bao base package de autowire duoc cac class
        //vi du nhu RabbitMQMessageProducer trong package com.unicoin.amqp cua module amqp
        scanBasePackages = {
                "com.unicoin.customer",
                "com.unicoin.amqp"
        }
)
@EnableEurekaClient
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}

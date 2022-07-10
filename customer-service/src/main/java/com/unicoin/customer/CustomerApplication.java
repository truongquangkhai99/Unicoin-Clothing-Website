package com.unicoin.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(
        //khai bao base package de autowire duoc cac class
        //vi du nhu RabbitMQMessageProducer trong package com.unicoin.amqp cua module amqp
        scanBasePackages = {
                "com.unicoin.customer",
                "com.unicoin.amqp"
        }
)
@EnableEurekaClient
@EnableAuthorizationServer
@EnableResourceServer
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}


package com.unicoin.order.config;

import com.unicoin.clients.commons.RabbitKey;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableRabbit
@Data
@Slf4j
public class OrderConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public DirectExchange exchange() {
//        log.info("internal-exchange: " + internalExchange);
        return new DirectExchange(RabbitKey.DIRECT_EXCHANGE);
    }

    @Bean
    public Queue exportOrderServiceQueue() {
//        log.info("order service Queue: " + this.orderServiceQueue);
        return new Queue(RabbitKey.QUEUE_ADD_EXPORT_ORDER);
    }
    @Bean
    public Queue importOrderServiceQueue() {
//        log.info("order service Queue: " + this.orderServiceQueue);
        return new Queue(RabbitKey.QUEUE_ADD_IMPORT_ORDER);
    }

    @Bean
    public Binding exportOrderBinding() {
//        log.info("order service Queue: {}, exchange: {}, routing key: " + orderRoutingKey, orderServiceQueue(), internalTopicExchange());
        return BindingBuilder
                .bind(exportOrderServiceQueue())
                .to(exchange())
                .with(RabbitKey.EXPORT_ORDER_ROUTING_KEYS);
    }

    @Bean
    public Binding importOrderBinding() {
//        log.info("order service Queue: {}, exchange: {}, routing key: " + orderRoutingKey, orderServiceQueue(), internalTopicExchange());
        return BindingBuilder
                .bind(importOrderServiceQueue())
                .to(exchange())
                .with(RabbitKey.IMPORT_ORDER_ROUTING_KEYS);
    }
    //config kieu exchange gom co:
    /**
     *  direct exchange : gui va nhan queue theo routing key chinh xac
     *  default exchange : ban chat la direct exchange nhung  k co ten
     *  fanout exchange: message se duoc gui den toan bo listener hien co trong service
     *  topic exchange: chuyen message theo su trung khop cua routing key va queue name
     *
     *
     *
     //    * */

}

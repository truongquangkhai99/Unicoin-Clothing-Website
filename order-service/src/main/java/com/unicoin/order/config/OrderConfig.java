package com.unicoin.order.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
@EnableRabbit
public class OrderConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Value(("${rabbitmq.exchanges.internal}"))
//    private String internalExchange;
//
//    @Value("${rabbitmq.queue.order-service}")
//    private String orderServiceQueue;
//
//    @Value("${rabbitmq.routing-keys.internal-order-service}")
//    private String internalOrderServiceRoutingKey;
//
//    @Bean
//    public TopicExchange internalTopicExchange(){
//        return new TopicExchange((this.internalExchange));
//    }
//
//    @Bean
//    public Queue orderServiceQueue(){
//        return new Queue(this.orderServiceQueue);
//    }
//
//    @Bean
//    public Binding internalOerderServiceBinding(){
//        return BindingBuilder
//                .bind(orderServiceQueue())
//                .to(internalTopicExchange())
//                .with(this.internalOrderServiceRoutingKey);
//    }
//
//    public String getInternalExchange() {
//        return internalExchange;
//    }
//
//    public String getOrderServiceQueue() {
//        return orderServiceQueue;
//    }
//
//    public String getInternalOrderServiceRoutingKey() {
//        return internalOrderServiceRoutingKey;
//    }

    public static  final String TOPIC_EXCHANGE_NAME = "topic-exchange";
    public static  final String DIRECT_EXCHANGE_NAME = "direct-exchange";

    public static final String TOPIC_QUEUE_ADD_ORDER = "order-queue-add-order";
    public static final String TOPIC_QUEUE_UPDATE_ORDER = "order-queue-update-order";


    //config kieu exchange gom co:
    /**
    *  direct exchange : gui va nhan queue theo routing key chinh xac
    *  default exchange : ban chat la direct exchange nhung  k co ten
    *  fanout exchange: message se duoc gui den toan bo listener hien co trong service
    *  topic exchange: chuyen message theo su trung khop cua routing key va queue name
    *
    *
    *
    * */
    @Bean
    public Declarables directBindings(){
        Queue addOrderQueue = new Queue(TOPIC_QUEUE_ADD_ORDER);
        Queue updateOrderQueue = new Queue(TOPIC_QUEUE_UPDATE_ORDER);

        DirectExchange directExchange = new DirectExchange(DIRECT_EXCHANGE_NAME);
        return new Declarables(
                addOrderQueue,
                updateOrderQueue,
                directExchange,
                BindingBuilder.bind(addOrderQueue).to(directExchange).with("topic-exchange-add-order"),
                BindingBuilder.bind(updateOrderQueue).to(directExchange).with("topic-exchange-update-order")
        );
    }
//    @Bean
//    public Declarables topicBindings(){
//        Queue addOrderQueue = new Queue(TOPIC_QUEUE_ADD_ORDER);
//        Queue updateOrderQueue = new Queue(TOPIC_QUEUE_UPDATE_ORDER);
//
//        TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE_NAME);
//        return new Declarables(
//                addOrderQueue,
//                updateOrderQueue,
//                topicExchange,
//                BindingBuilder.bind(addOrderQueue).to(topicExchange).with("*-add-order"),
//                BindingBuilder.bind(updateOrderQueue).to(topicExchange).with("topic-exchange-update-order")
//        );
//    }
}

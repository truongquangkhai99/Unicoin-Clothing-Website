package com.unicoin.order.rabbitmq;

import com.unicoin.clients.form.orderform.OrderRequest;
import com.unicoin.order.config.OrderConfig;
import com.unicoin.order.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class OrderConsumer {

    @Autowired
    OrderService orderService;


//    @RabbitListener(queues = "${rabbitmq.queue.order-service}")
//    public void comsumer(OrderRequest orderRequest){
//    log.info("Consumed {} from queue", orderRequest);
//    orderService.saveOrder(orderRequest);
//    }

    //listener nhan request theo queue duoc con fig trong class config
    @RabbitListener(queues = OrderConfig.TOPIC_QUEUE_ADD_ORDER)
    public void comsumerAdd(OrderRequest orderRequest){
    log.info("Consumed ADD {} from queue", orderRequest);
    orderService.saveOrder(orderRequest);
    }

    @RabbitListener(queues = OrderConfig.TOPIC_QUEUE_UPDATE_ORDER)
    public void comsumerUpdate(OrderRequest orderRequest){
    log.info("Consumed UPDATE {} from queue", orderRequest);
    orderService.saveOrder(orderRequest);
    }
}

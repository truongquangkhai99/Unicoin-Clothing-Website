package com.unicoin.order.rabbitmq;

import com.unicoin.clients.commons.RabbitKey;
import com.unicoin.clients.rabbitmqModel.QueueExportOrder;
import com.unicoin.clients.rabbitmqModel.QueueImportOrder;
import com.unicoin.clients.rabbitmqModel.QueueReturnOrder;
import com.unicoin.order.service.ExportOrderService;
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
    ExportOrderService orderService;


//    @RabbitListener(queues = "${rabbitmq.queue.order-service}")
//    public void comsumer(OrderRequest orderRequest){
//    log.info("Consumed {} from queue", orderRequest);
//    orderService.saveOrder(orderRequest);
//    }

    //    @RabbitListener(queues = OrderConfig.TOPIC_QUEUE_UPDATE_ORDER)
//    public void comsumerUpdate(OrderRequest orderRequest){
//    log.info("Consumed UPDATE {} from queue", orderRequest);
//    orderService.saveOrder(orderRequest);
//    }


    //listener nhan request theo ROUTING_KEY duoc con fig trong class config
    @RabbitListener(queues = RabbitKey.QUEUE_ADD_EXPORT_ORDER)
    public void comsumerAddExportOrder(QueueExportOrder order){
    log.info("Consumed add export order {} from queue", order);
    orderService.addExportOrder(order);
    }

    @RabbitListener(queues = RabbitKey.QUEUE_ADD_IMPORT_ORDER)
    public void comsumerAddImportOrder(QueueImportOrder order){
//        orderService.addImportOrderFromQueues(order);
        log.info("Consumed add import order {} from queue", order);
    }

//    @RabbitListener(queues = RabbitKey.QUEUE_ADD_IMPORT_ORDER)
//    public void comsumerAddReturnOrder(QueueReturnOrder order){
//        log.info("Consumed add return order {} from queue", order);
//    }
}

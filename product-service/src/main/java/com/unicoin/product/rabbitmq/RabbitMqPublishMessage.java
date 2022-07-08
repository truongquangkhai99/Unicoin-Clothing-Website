package com.unicoin.product.rabbitmq;

import com.unicoin.amqp.RabbitMQMessageProducer;
import com.unicoin.clients.commons.RabbitKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class RabbitMqPublishMessage {

//    @Autowired
//    RabbitMQMessageProducer producer;
//
//    //ban hang
//    public void addExportOrder(Object object){
//       try {
//           producer.publish(object, RabbitKey.ROUTING_KEY_EXPORT_ORDER, RabbitKey.QUEUE_ADD_EXPORT_ORDER);
//       }catch (Exception e){
//           e.printStackTrace();
//       }
//    }
//
//    //nhap hang
//    public void addImportOrder(Object object){
//        producer.publish(object, RabbitKey.ROUTING_KEY_EXPORT_ORDER, RabbitKey.QUEUE_ADD_IMPORT_ORDER);
//    }
//
//    //tra hang
//    public void addReturnOrder(Object object){
//        producer.publish(object, RabbitKey.ROUTING_KEY_EXPORT_ORDER, RabbitKey.QUEUE_ADD_RETURN_ORDER);
//    }
}

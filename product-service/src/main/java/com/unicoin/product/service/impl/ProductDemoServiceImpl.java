package com.unicoin.product.service.impl;

import com.unicoin.amqp.RabbitMQMessageProducer;
import com.unicoin.clients.customer.CustomerFeign;
import com.unicoin.clients.form.orderform.OrderRequest;
import com.unicoin.clients.orders.OrderFeign;
import com.unicoin.clients.resstresponse.ResponseCheckCustomer;
import com.unicoin.clients.form.productform.ProductDemoForm;
import com.unicoin.product.entity.ProductDemo;
import com.unicoin.product.service.ProductDemoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
@Slf4j
@AllArgsConstructor
public class ProductDemoServiceImpl implements ProductDemoService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerFeign customerFeign;

    @Autowired
    OrderFeign orderFeign;

    //phai khai bao base package o ProductManageApplication
    private final RabbitMQMessageProducer producer;

    @Override
    public ProductDemo addProduct(ProductDemoForm productDemoForm) {
//        log.info("customer Name " + productDemoForm.getProductName());
//        //call api qua resttempalte: param 1: api // param2: kieu du lieu tra ve // param3: tham so truyen vao
////        ResponseCheckCustomer responseCheckCustomer = restTemplate.getForObject("http://CUSTOMER-SERVICE/api/demo/customers/check-customer/{customerFistName}",
////                ResponseCheckCustomer.class,
////                productDemoForm.getCustomerFistName());
//        ResponseCheckCustomer responseCheckCustomer = customerFeign.checkCustomer(productDemoForm.getCustomerFistName());
//        if (responseCheckCustomer.isCheckCustomer()){
//            ProductDemo productDemo = ProductDemo.builder()
//                    .productName(productDemoForm.getProductName())
//                    .price(productDemoForm.getPrice())
//                    .customerFistName(productDemoForm.getCustomerFistName())
//                    .build();
//            log.info("Product {}", productDemo);
//            return productDemo;
//        }else {
            return null;
//        }
    }

    @Override
    public void sendAddOrder() {
//        OrderRequest orderRequest = OrderRequest.builder()
//                .customerFistName("hai")
//                .createAt(new Date())
//                .build();
        //add order synchronized
//        OrderRequest orderResp = orderFeign.createOrder(orderRequest);

        //add order async
//        log.info("order add: {}, order name: {}", orderRequest, orderRequest.getCustomerFistName());
//        producer.publish(orderRequest, "direct-exchange", "topic-exchange-add-order");
    }
    @Override
    public void sendUpdateOrder() {
//        OrderRequest orderRequest = OrderRequest.builder()
//                .customerFistName("hai")
//                .createAt(new Date())
//                .build();
        //add order synchronized
//        OrderRequest orderResp = orderFeign.createOrder(orderRequest);

        //add order async
//        log.info("order update: {}, order name: {}", orderRequest, orderRequest.getCustomerFistName());
//        producer.publish(orderRequest, "direct-exchange", "topic-exchange-update-order");
    }
}

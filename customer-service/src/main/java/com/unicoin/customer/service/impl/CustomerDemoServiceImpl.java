package com.unicoin.customer.service.impl;

import com.unicoin.amqp.RabbitMQMessageProducer;
import com.unicoin.customer.ex.AppException;
import com.unicoin.customer.ex.ExceptionCode;
import com.unicoin.clients.form.customerform.CustomerDemoForm;
import com.unicoin.customer.service.CustomerDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CustomerDemoServiceImpl implements CustomerDemoService {

    @Autowired
    RestTemplate restTemplate;


    //send queue
    @Autowired
    RabbitMQMessageProducer rabbitMQMessageProducer;

    @Override
    public void registrationCustomer(CustomerDemoForm customerDemoForm) {
//        CustomerDemo customerDemo = CustomerDemo.builder()
//                .customerId(customerDemoForm.getCustomerId())
//                .fistName(customerDemoForm.getFistName())
//                .lastName(customerDemoForm.getLastName())
//                .email(customerDemoForm.getEmail())
//                .build();
        throw new AppException(ExceptionCode.INVALID_VALUE);
    }


    @Override
    public boolean checkCustomer(String customerFistName) {
        if ("hai".equalsIgnoreCase(customerFistName)) return true;
        else return false;
    }
}

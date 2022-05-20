package com.unicoin.order.controller;

import com.unicoin.clients.form.orderform.OrderRequest;
import com.unicoin.order.resstresponse.ApiResponse;
import com.unicoin.order.resstresponse.SuccessResponse;
import com.unicoin.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo/orders")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("")
    public ApiResponse createOrder(@RequestBody OrderRequest request){
        log.info("Order request {}", request);
        orderService.saveOrder(request);
        return new SuccessResponse();
    }
}

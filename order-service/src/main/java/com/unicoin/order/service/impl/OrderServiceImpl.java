package com.unicoin.order.service.impl;

import com.unicoin.clients.form.orderform.OrderRequest;
import com.unicoin.order.entity.Order;
import com.unicoin.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public void saveOrder(OrderRequest request) {
        Order order = Order.builder()
                .createAt(request.getCreateAt())
                .customerFistName(request.getCustomerFistName())
                .build();
        OrderRequest orderRequest = new OrderRequest();
        BeanUtils.copyProperties(order, orderRequest);
    }
}

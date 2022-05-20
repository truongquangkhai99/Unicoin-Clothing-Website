package com.unicoin.order.service;

import com.unicoin.clients.form.orderform.OrderRequest;

public interface OrderService {
    void saveOrder(OrderRequest request);
}

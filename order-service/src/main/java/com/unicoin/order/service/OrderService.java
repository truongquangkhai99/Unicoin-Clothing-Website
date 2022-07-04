package com.unicoin.order.service;

import com.unicoin.clients.form.orderform.OrderRequest;
import com.unicoin.order.config.ImportOrdersDTO;
import com.unicoin.order.entity.ImportOrders;
import com.unicoin.order.form.AddImportOrderDetail;

import java.util.List;

public interface OrderService {
    ImportOrdersDTO viewsImportOrders();
    void AddImportOrderDetail(List<AddImportOrderDetail> addImportOrderDetail , Long importOrderId);
    void saveOrder(OrderRequest request);

    void updateOrderDetail(Long orderId , Integer status);
}

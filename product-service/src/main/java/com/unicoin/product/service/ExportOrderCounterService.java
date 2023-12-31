package com.unicoin.product.service;

import com.unicoin.product.entity.ExportOrder;
import com.unicoin.product.form.AddExportOrderDetail;
import com.unicoin.product.form.CheckoutExportOrders;

import java.util.List;

public interface ExportOrderCounterService {
    ExportOrder createExportOrder(CheckoutExportOrders addExportOrders);

    void deleteExportOrder(Long orderId);

    void addProductToOrderDetailCounter(AddExportOrderDetail addExportOrderDetail);

    void deleteOrderDetailCounter(Long id);

    void updateOrderDetailCounter(Long id, Integer quantity);

    List<Long> listIdExportOrderCounter(Integer status, Integer type);

    void checkoutOrder(CheckoutExportOrders orders);
}

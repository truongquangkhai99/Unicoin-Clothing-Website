package com.unicoin.product.service;

import com.unicoin.product.entity.ExportOrder;
import com.unicoin.product.form.AddExportOrderDetail;
import com.unicoin.product.form.AddExportOrders;

public interface ExportOrderCounterService {
    ExportOrder createExportOrder(AddExportOrders addExportOrders);

    void deleteExportOrder(Long orderId);

    void addProductToOrderDetailCounter(AddExportOrderDetail addExportOrderDetail);

    void deleteOrderDetailCounter(Long id);

    void updateOrderDetailCounter(Long id, Integer quantity);
}
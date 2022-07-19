package com.unicoin.product.service;

import com.unicoin.product.dto.ExportOrderDTO;
import com.unicoin.product.dto.ExportOrderDetailDTO;
import com.unicoin.product.entity.ExportOrder;
import com.unicoin.product.entity.ExportOrderDetail;
import com.unicoin.product.form.AddExportOrderDetail;
import com.unicoin.product.form.CheckoutExportOrders;

import java.util.List;

public interface ExportOrderService {
    List<ExportOrder> viewsAllExportOrder(String userUserPhoneNumber);

    ExportOrderDTO addExportOrder(Long orderId);

    void addExportOrderDetail(AddExportOrderDetail addExportOrderDetail);

    void updateExportOrder(Long exportOrderId, Integer status);

    void deleteExportOrderDetail(Long id);

    void checkoutOrder(CheckoutExportOrders checkoutExportOrders);

    List<ExportOrderDetailDTO> viewExportOrderByOrderId(Long orderId);
}

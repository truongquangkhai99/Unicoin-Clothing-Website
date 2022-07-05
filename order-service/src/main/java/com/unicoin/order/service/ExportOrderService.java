package com.unicoin.order.service;

import com.unicoin.order.entity.ExportOrder;
import com.unicoin.order.entity.ExportOrderDetail;
import com.unicoin.order.form.AddExportOrders;
import com.unicoin.order.form.FormExportOrderDetail;

import java.util.List;

public interface ExportOrderService {
    List<ExportOrder> viewsAllExportOrder(Integer userId);
    public void addExportOrderDetail(AddExportOrders addExportOrders);
    List<ExportOrderDetail> viewsAllExportOrderDetail(Long exportOrderId);
    public void updateExportOrder(Long exportOrderId, Integer status);
}

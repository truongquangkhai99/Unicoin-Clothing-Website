package com.unicoin.product.service;

import com.unicoin.product.entity.ExportOrder;
import com.unicoin.product.entity.ExportOrderDetail;
import com.unicoin.product.form.AddExportOrderDetail;
import com.unicoin.product.form.AddExportOrders;

import java.util.List;

public interface ExportOrderService {
    List<ExportOrder> viewsAllExportOrder(Integer userId);

    ExportOrder addExportOrder(AddExportOrders addExportOrders);
    public void addExportOrderDetail(AddExportOrderDetail addExportOrderDetail);
    List<ExportOrderDetail> viewsAllExportOrderDetail(Long goodIssueId);
    public void updateExportOrder(Long goodIssueId, Integer status);
    public void deleteExportOrderDetail(Long id);
}

package com.unicoin.product.service;

import com.unicoin.clients.form.orderform.OrderRequest;
import com.unicoin.product.common.RestResponsePage;
import com.unicoin.product.dto.ImportOrdersDTO;
import com.unicoin.product.entity.ImportOrders;
import com.unicoin.product.form.AddImportOrderDetail;

import java.util.List;

public interface ImportOrdersService {
    ImportOrdersDTO viewsImportOrders();
    void AddImportOrderDetail(List<AddImportOrderDetail> addImportOrderDetail , Long importOrdersId);

    void updateOrderDetail(Long orderId , Integer status);

    void deleteOrderDetail(Long id);
}

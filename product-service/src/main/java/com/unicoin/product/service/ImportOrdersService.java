package com.unicoin.product.service;

import com.unicoin.product.dto.ImportOrdersDTO;
import com.unicoin.product.form.AddImportOrderDetail;

import java.util.List;

public interface ImportOrdersService {
    ImportOrdersDTO viewsImportOrders();
    void AddImportOrderDetail(List<AddImportOrderDetail> addImportOrderDetail , Long importOrderId);
}

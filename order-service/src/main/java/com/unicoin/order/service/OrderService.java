package com.unicoin.order.service;

import com.unicoin.clients.form.orderform.OrderRequest;
import com.unicoin.order.DTO.ImportOrderDTO;
import com.unicoin.order.DTO.ImportOrderDetailDTO;
import com.unicoin.order.config.ImportOrdersDTO;
import com.unicoin.order.form.AddImportOrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface OrderService {
    ImportOrdersDTO viewsImportOrders();
    void AddImportOrderDetail(List<AddImportOrderDetail> addImportOrderDetail , Long importOrdersId);
    void saveOrder(OrderRequest request);

    void updateOrderDetail(Long orderId , Integer status);

    List<ImportOrderDTO> getImportOrderByStatus(Integer status);

    List<ImportOrderDetailDTO> getImportOrderDetailByImportOrdersId(Long id);
}

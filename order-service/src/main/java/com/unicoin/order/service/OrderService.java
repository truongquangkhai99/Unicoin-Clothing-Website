package com.unicoin.order.service;

import com.unicoin.clients.rabbitmqModel.QueueImportOrder;
import com.unicoin.order.DTO.ImportOrdersDTO;
import com.unicoin.order.DTO.ImportOrderDetailDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface OrderService {

    void addImportOrderFromQueues(QueueImportOrder queueImportOrder);

    void updateOrderByStatus(Long orderId, Integer status);

    List<ImportOrdersDTO> getImportOrderByStatus(Integer status);

    List<ImportOrderDetailDTO> getImportOrderDetailByImportOrdersId(Long id);
}

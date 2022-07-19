package com.unicoin.order.service;

import com.unicoin.clients.rabbitmqModel.QueueExportOrder;
import com.unicoin.order.DTO.ExportOrderDTO;
import com.unicoin.order.DTO.ExportOrderDetailDTO;
import com.unicoin.order.common.RestResponsePage;
import com.unicoin.order.entity.ExportOrder;
import com.unicoin.order.entity.ExportOrderDetail;
import com.unicoin.order.form.AddExportOrders;
import com.unicoin.order.form.FormExportOrderDetail;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExportOrderService {
    RestResponsePage<ExportOrderDTO> guestViewsAllExportOrderByUserPhoneNumber();
    RestResponsePage<ExportOrderDetailDTO> guestViewsAllExportOrderDetail(Long orderId);
    public void addExportOrder(QueueExportOrder queueExportOrder);
    public void updateExportOrder(Long exportOrderId, Integer status);

    RestResponsePage<ExportOrderDTO> viewsAllExportOrder(Integer status);
}

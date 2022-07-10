package com.unicoin.order.service;

import com.unicoin.order.DTO.ExportOrderDTO;
import com.unicoin.order.DTO.ExportOrderDetailDTO;
import com.unicoin.order.entity.ExportOrder;
import com.unicoin.order.entity.ExportOrderDetail;
import com.unicoin.order.form.AddExportOrders;
import com.unicoin.order.form.FormExportOrderDetail;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ExportOrderService {
    List<ExportOrder> viewsAllExportOrder(Integer userId);
    public void addExportOrderDetail(AddExportOrders addExportOrders);
    public void updateExportOrder(Long exportOrderId, Integer status);

    List<ExportOrderDTO> getExportOrderByStatus(Integer status);

    List<ExportOrderDetailDTO> getExportOrderDetailByExportOrderId(Long id);

    List<ExportOrder> viewExportOrderByUserId(Long id);


    List<ExportOrder> viewExportOrderByOption(Long id);
}

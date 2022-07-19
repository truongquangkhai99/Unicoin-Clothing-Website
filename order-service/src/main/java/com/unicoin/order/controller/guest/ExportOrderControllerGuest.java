package com.unicoin.order.controller.guest;

import com.unicoin.order.restresponse.ApiResponse;
import com.unicoin.order.restresponse.SuccessResponse;
import com.unicoin.order.service.ExportOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report/export-order")
public class ExportOrderControllerGuest {
    @Autowired
    ExportOrderService exportOrderService;

    @GetMapping("")
    public ApiResponse viewsExportOrder(){
        return new SuccessResponse(exportOrderService.guestViewsAllExportOrderByUserPhoneNumber());
    }

    @GetMapping("/{export-order-id}")
    public ApiResponse viewsExportOrderDetail(@PathVariable Long exportOrderId){
        return new SuccessResponse(exportOrderService.guestViewsAllExportOrderDetail(exportOrderId));
    }

    @GetMapping("/cancel/{exportOrderId}")
    public ApiResponse cancelExportOrder(@PathVariable Long exportOrderId){
        //status = 2 = cancel
        exportOrderService.updateExportOrder(exportOrderId, 2);
        return new SuccessResponse();
    }
}

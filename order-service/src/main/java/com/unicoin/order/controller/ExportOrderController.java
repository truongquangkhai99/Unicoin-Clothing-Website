package com.unicoin.order.controller;

import com.unicoin.order.form.AddExportOrders;
import com.unicoin.order.restresponse.ApiResponse;
import com.unicoin.order.restresponse.SuccessResponse;
import com.unicoin.order.service.ExportOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/exportOrder")
public class ExportOrderController {
    @Autowired
    ExportOrderService exportOrderService;

    @GetMapping("")
    public ApiResponse viewsExportOrder(@RequestParam(required = false) Integer status){
        return new SuccessResponse(exportOrderService.viewsAllExportOrder(status));
    }

    @GetMapping("/{exportOrderId}")
    public ApiResponse viewsExportOrderDetail(@PathVariable Long exportOrderId){
        return new SuccessResponse(exportOrderService.guestViewsAllExportOrderDetail(exportOrderId));
    }

    @PutMapping("/update")
    public ApiResponse updateExportOrder(@RequestParam(value = "exportOrderId") Long exportOrderId,
                                         @RequestParam(value="status") Integer status){
        exportOrderService.updateExportOrder(exportOrderId,status);
        return new SuccessResponse();
    }
}

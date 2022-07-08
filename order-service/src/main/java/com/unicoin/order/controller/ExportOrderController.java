package com.unicoin.order.controller;

import com.unicoin.order.form.AddExportOrders;
import com.unicoin.order.resstresponse.ApiResponse;
import com.unicoin.order.resstresponse.SuccessResponse;
import com.unicoin.order.service.ExportOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/exportOrder")
public class ExportOrderController {
    @Autowired
    ExportOrderService exportOrderService;

    @GetMapping("")
    public ApiResponse viewsExportOrder(@RequestParam(value = "userId") Integer userId){

        return new SuccessResponse(exportOrderService.viewsAllExportOrder(userId));
    }

    @PostMapping("/add/{export_order_id}")
    public ApiResponse createExportOrder(@RequestBody AddExportOrders addExportOrders ){
        exportOrderService.addExportOrderDetail(addExportOrders);
        return new SuccessResponse();
    }

    @PutMapping("/update")
    public ApiResponse updateExportOrder(@RequestParam(value = "exportOrderId") Long exportOrderId, @RequestParam
            (value="status") Integer status){
        exportOrderService.updateExportOrder(exportOrderId,status);
        return new SuccessResponse();
    }
}
package com.unicoin.product.controller.admin;

import com.unicoin.product.form.AddExportOrderDetail;
import com.unicoin.product.form.CheckoutExportOrders;
import com.unicoin.product.resstresponse.ApiResponse;
import com.unicoin.product.resstresponse.SuccessResponse;
import com.unicoin.product.service.ExportOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/export-order")

public class ExportOrderController {
    @Autowired
    ExportOrderService exportOrderService;


    @PostMapping("/add/export-order")
    public ApiResponse createExportOrder() {
        return new SuccessResponse(exportOrderService.addExportOrder());
    }

    @PostMapping("/checkout")
    public ApiResponse checkoutExportOrder(@RequestBody CheckoutExportOrders checkoutExportOrders){
        exportOrderService.checkoutOrder(checkoutExportOrders);
        return new SuccessResponse(exportOrderService.addExportOrder());
    }

    @PostMapping("/add/export-order-detail")
    public ApiResponse createExportOrderDetail(@RequestBody AddExportOrderDetail addExportOrderDetail){
        exportOrderService.addExportOrderDetail(addExportOrderDetail);
        return new SuccessResponse(exportOrderService.addExportOrder());
    }

    @GetMapping("/view-export-order/{orderId}")
    public ApiResponse viewExportOrder(@PathVariable("orderId") Long orderId){
        return new SuccessResponse(exportOrderService.viewExportOrderByOrderId(orderId));
    }

}

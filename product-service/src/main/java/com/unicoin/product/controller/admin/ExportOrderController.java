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


    @GetMapping("/add/export-order")
    public ApiResponse createExportOrder(@RequestParam(required = false) Long orderId) {
        return new SuccessResponse(exportOrderService.addExportOrder(orderId));
    }

    @PostMapping("/checkout")
    public ApiResponse checkoutExportOrder(@RequestBody CheckoutExportOrders checkoutExportOrders){
        exportOrderService.checkoutOrder(checkoutExportOrders);
        return new SuccessResponse();
    }

    @PostMapping("/add/export-order-detail")
    public ApiResponse createExportOrderDetail(@RequestBody AddExportOrderDetail addExportOrderDetail){
        exportOrderService.addExportOrderDetail(addExportOrderDetail);
        return new SuccessResponse();
    }

    @GetMapping("/view-export-order/{orderId}")
    public ApiResponse viewExportOrder(@PathVariable("orderId") Long orderId){
        return new SuccessResponse(exportOrderService.viewExportOrderByOrderId(orderId));
    }
    @GetMapping("/sum-price-orde/{id}")
    public  ApiResponse sumPriceOrder(@PathVariable("id") Long id){
        return  new SuccessResponse((exportOrderService.sumOderPrice(id)));
    }

}

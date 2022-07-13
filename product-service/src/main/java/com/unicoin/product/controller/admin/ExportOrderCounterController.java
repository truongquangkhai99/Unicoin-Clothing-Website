package com.unicoin.product.controller.admin;


import com.unicoin.product.form.AddExportOrderDetail;
import com.unicoin.product.form.CheckoutExportOrders;
import com.unicoin.product.resstresponse.ApiResponse;
import com.unicoin.product.resstresponse.SuccessResponse;
import com.unicoin.product.service.ExportOrderCounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/export-order-counter")
public class ExportOrderCounterController {
    @Autowired
    ExportOrderCounterService exportOrderService;

    @GetMapping("/create-export-order")
    public ApiResponse createOrder(@RequestBody CheckoutExportOrders exportOrders){
        return new SuccessResponse(exportOrderService.createExportOrder(exportOrders));
    }

    @DeleteMapping("/delete-export-order/{exportOrderId}")
    public ApiResponse deleteOrder(@PathVariable("exportOrderId") Long id){
        exportOrderService.deleteExportOrder(id);
        return new SuccessResponse();
    }

    @PostMapping("/add-product-to-order")
    public ApiResponse addOrderDetail(@RequestBody AddExportOrderDetail addExportOrderDetail){
        exportOrderService.addProductToOrderDetailCounter(addExportOrderDetail);
        return new SuccessResponse();
    }

    @PutMapping("/update-product-order")
    public  ApiResponse updateProductOrder(@RequestParam("exportOrderDetailId") Long id,
                                           @RequestParam("quantity") Integer quantity){
        exportOrderService.updateOrderDetailCounter(id, quantity);
        return new SuccessResponse();
    }

    @DeleteMapping("/delete-product-order/{variantId}")
    public ApiResponse deleteProductOrder(@PathVariable Long variantId){
        exportOrderService.deleteOrderDetailCounter(variantId);
        return new SuccessResponse();
    }



}

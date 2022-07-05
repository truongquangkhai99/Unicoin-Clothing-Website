package com.unicoin.product.controller.admin;

import com.unicoin.product.form.AddExportOrderDetail;
import com.unicoin.product.form.AddExportOrders;
import com.unicoin.product.resstresponse.ApiResponse;
import com.unicoin.product.resstresponse.SuccessResponse;
import com.unicoin.product.service.ExportOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/exportOrder")

public class ExportOrderController {
    @Autowired
    ExportOrderService exportOrderService;


    @GetMapping("/viewsExportOrder")
    public ApiResponse viewsExportOrder(@RequestParam(value = "userId") Integer userId){
        return new SuccessResponse(exportOrderService.viewsAllExportOrder(userId));
    }

    @GetMapping("/viewsExportOrderDetail")
    public ApiResponse viewsAllExportOrderDetail(@RequestParam(value="exportOrderId") Long exportOrderId){
       return new SuccessResponse(exportOrderService.viewsAllExportOrderDetail(exportOrderId));
    }

    @PutMapping("/update")
    public ApiResponse updateExportOrder(@RequestParam(value = "exportOrderId") Long exportOrderId, @RequestParam
            (value="status") Integer status){
        exportOrderService.updateExportOrder(exportOrderId,status);
        return new SuccessResponse();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteExportOrder(@PathVariable Long id){
        exportOrderService.deleteExportOrderDetail(id);
        return new SuccessResponse();
    }

    @PostMapping("/add/exportOrder")
    public ApiResponse createExportOrder(@RequestBody AddExportOrders addExportOrders ) {
        return new SuccessResponse(exportOrderService.addExportOrder(addExportOrders));
    }
    @PostMapping("/add/exportOrderDetail")
    public ApiResponse createExportOrderDetail(@RequestBody AddExportOrderDetail addExportOrderDetail){
        exportOrderService.addExportOrderDetail(addExportOrderDetail);
        return new SuccessResponse();
    }
}

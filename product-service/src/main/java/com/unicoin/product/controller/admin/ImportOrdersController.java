package com.unicoin.product.controller.admin;

import com.unicoin.product.form.AddImportOrderDetail;
import com.unicoin.product.resstresponse.ApiResponse;
import com.unicoin.product.resstresponse.SuccessResponse;
import com.unicoin.product.service.ImportOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/orders")
public class ImportOrdersController {

    @Autowired
    ImportOrdersService importOrdersService;

    @GetMapping("")
    public ApiResponse viewsImportOrders(){
        return new SuccessResponse(importOrdersService.viewsImportOrders());
    }

    @PostMapping("/add/{importOrderId}")
    public ApiResponse createOrder(@RequestBody List<AddImportOrderDetail> addImportOrderDetail
            ,@PathVariable Long importOrderId ){
        importOrdersService.AddImportOrderDetail(addImportOrderDetail , importOrderId);
        return new SuccessResponse();
    }

    @PutMapping("/update")
    public ApiResponse updateOrders( @RequestParam(value = "importId") Long importId, @RequestParam(value = "status") Integer status){
        importOrdersService.updateOrderDetail(importId , status);
        return new SuccessResponse();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteOrders(@PathVariable Long id){
        importOrdersService.deleteOrderDetail(id);
        return new SuccessResponse();
    }
}
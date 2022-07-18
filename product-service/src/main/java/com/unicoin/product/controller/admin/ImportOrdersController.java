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

    @PostMapping("/add/{importOrdersId}")
    public ApiResponse createOrder(@RequestBody List<AddImportOrderDetail> addImportOrderDetail
            ,@PathVariable Long importOrdersId ){
        importOrdersService.AddImportOrderDetail(addImportOrderDetail , importOrdersId);
        return new SuccessResponse();
    }
    @GetMapping("/sum-price-orde/{id}")
    public  ApiResponse sumPriceOrder(@PathVariable("id") Long id){
        return  new SuccessResponse((importOrdersService.sumPriceImportOrder(id)));
    }

    @PostMapping("/checkout/{importOrderId}")
    public ApiResponse checkoutImportOrder(@PathVariable Long importOrderId){
        importOrdersService.checkoutImportOrder(importOrderId);
        return new SuccessResponse();
    }
}

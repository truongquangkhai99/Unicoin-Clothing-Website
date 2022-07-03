package com.unicoin.order.controller;

import com.unicoin.clients.form.orderform.OrderRequest;
import com.unicoin.order.form.AddImportOrderDetail;
import com.unicoin.order.resstresponse.ApiResponse;
import com.unicoin.order.resstresponse.SuccessResponse;
import com.unicoin.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/orders")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("")
    public ApiResponse viewsImportOrders(){
        return  new SuccessResponse(orderService.viewsImportOrders());
    }

    @PostMapping("/add/{importOrderId}")
    public ApiResponse createOrder(@RequestBody List<AddImportOrderDetail> addImportOrderDetail
                                    ,@PathVariable Long importOrderId ){
        orderService.AddImportOrderDetail(addImportOrderDetail , importOrderId);
      return new SuccessResponse();
    }

    @PutMapping("/update")
            public ApiResponse updateOrders( @RequestParam(value = "importId") Long importId, @RequestParam(value = "status") Integer status){
        orderService.updateOrderDetail(importId , status);
        return new SuccessResponse();
    }
}

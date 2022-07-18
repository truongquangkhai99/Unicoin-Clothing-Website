package com.unicoin.order.controller;

import com.unicoin.order.form.AddImportOrderDetail;
import com.unicoin.order.restresponse.ApiResponse;
import com.unicoin.order.restresponse.SuccessResponse;
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

    @PutMapping("/update")
            public ApiResponse updateOrders( @RequestParam(value = "importId") Long importId, @RequestParam(value = "status") Integer status){

        return new SuccessResponse();
    }

    @GetMapping("/status")
    public ApiResponse getImportOrderByStatus(@RequestParam(value="status") Integer status){
        return new SuccessResponse(orderService.getImportOrderByStatus(status));
    }

    @GetMapping("/{id}")
    public ApiResponse getImportOrderDetailByImportOrdersId(@PathVariable Long id){
        return new SuccessResponse(orderService.getImportOrderDetailByImportOrdersId(id));
    }
}

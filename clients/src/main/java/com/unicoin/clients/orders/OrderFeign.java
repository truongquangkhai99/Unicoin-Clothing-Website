package com.unicoin.clients.orders;

import com.unicoin.clients.form.orderform.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("order")
public interface OrderFeign {

    @PostMapping("/api/demo/orders")
    OrderRequest createOrder(@RequestBody OrderRequest request);
}

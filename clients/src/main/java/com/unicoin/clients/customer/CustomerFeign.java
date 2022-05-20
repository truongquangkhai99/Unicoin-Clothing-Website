package com.unicoin.clients.customer;

import com.unicoin.clients.resstresponse.ResponseCheckCustomer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("customer-service")
public interface CustomerFeign {

    @GetMapping("/api/demo/customers/check-customer/{customerFistName}")
    ResponseCheckCustomer checkCustomer(@PathVariable("customerFistName") String customerFistName);

}

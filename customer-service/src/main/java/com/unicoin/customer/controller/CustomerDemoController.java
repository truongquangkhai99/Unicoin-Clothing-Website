package com.unicoin.customer.controller;

import com.unicoin.customer.resstresponse.ResponseCheckCustomer;
import com.unicoin.clients.form.customerform.CustomerDemoForm;
import com.unicoin.customer.resstresponse.ApiResponse;
import com.unicoin.customer.resstresponse.SuccessResponse;
import com.unicoin.customer.service.CustomerDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/demo/customers")
public class CustomerDemoController {

    @Autowired
    CustomerDemoService customerDemoService;

    @PostMapping("")
    public ApiResponse registerCustomer(@RequestBody CustomerDemoForm customerDemoForm){
        log.info("new customer registration {}", customerDemoForm);
        customerDemoService.registrationCustomer(customerDemoForm);
        return new SuccessResponse();
    }

    @GetMapping("/check-customer/{customerFistName}")
    public ResponseCheckCustomer checkCustomer(@PathVariable String customerFistName){
        log.info("check customer {}", customerFistName);
        return new ResponseCheckCustomer(customerDemoService.checkCustomer(customerFistName));
    }

}

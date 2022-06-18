package com.unicoin.product.controller.admin;


import com.unicoin.clients.form.productform.ProductDemoForm;
import com.unicoin.product.resstresponse.ApiResponse;
import com.unicoin.product.resstresponse.SuccessResponse;
import com.unicoin.product.service.ProductDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo/product-manage")
public class ProductDemoManageController {

    @Autowired
    ProductDemoService productDemoService;

    @PostMapping("")
    public ApiResponse createProduct(@RequestBody ProductDemoForm productDemoForm){
        return new SuccessResponse(productDemoService.addProduct(productDemoForm));
    }

    @PostMapping("addOrder")
    public ApiResponse sendAddOrder(){
        productDemoService.sendAddOrder();
        return new SuccessResponse();
    }

    @PostMapping("updateOrder")
    public ApiResponse sendUpdateOrder(){
        productDemoService.sendUpdateOrder();
        return new SuccessResponse();
    }
}

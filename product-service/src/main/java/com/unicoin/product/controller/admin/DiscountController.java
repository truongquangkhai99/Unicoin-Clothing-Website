package com.unicoin.product.controller.admin;

import com.unicoin.product.repository.ProductRepository;
import com.unicoin.product.repository.VariantRepository;
import com.unicoin.product.resstresponse.ApiResponse;
import com.unicoin.product.resstresponse.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/discount")
public class DiscountController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariantRepository variantRepository;

    @PostMapping("/by-product")
    public ApiResponse discountByProduct(){
        return new SuccessResponse();
    }
}

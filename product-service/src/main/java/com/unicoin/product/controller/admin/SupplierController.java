package com.unicoin.product.controller.admin;

import com.unicoin.product.form.AddSupplierForm;
import com.unicoin.product.resstresponse.ApiResponse;
import com.unicoin.product.resstresponse.SuccessResponse;
import com.unicoin.product.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/supplier")
@CrossOrigin("*")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping("")
    public ApiResponse getAllSuppliers(){
        return new SuccessResponse(supplierService.getAllSupplier());
    }

    @PostMapping("")
    public ApiResponse saveSupplier(@RequestBody AddSupplierForm form){
        supplierService.addSupplier(form);
        return new SuccessResponse();
    }
}

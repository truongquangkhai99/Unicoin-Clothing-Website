package com.unicoin.customer.controller;

import com.unicoin.customer.form.AddAddressForm;
import com.unicoin.customer.resstresponse.ApiResponse;
import com.unicoin.customer.resstresponse.SuccessResponse;
import com.unicoin.customer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/admin/customer/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/{userId}")
    public ApiResponse viewsAddress(@PathVariable(value = "userId", required = true) Long userId){
       return new SuccessResponse(addressService.viewsAddress(userId)) ;
    }

    @PostMapping("/add")
    public ApiResponse addAddress(@Valid @RequestBody AddAddressForm addAddressForm){
        addressService.addAddress(addAddressForm);
        return new SuccessResponse();
    }

    @PutMapping("/update/{addressId}")
    public ApiResponse updateAddress(
            @PathVariable(value = "addressId") Long addressId ,
           @Valid @RequestBody AddAddressForm addAddressForm){
        addressService.updateAddress(addressId , addAddressForm);
        return  new SuccessResponse();
    }

    @DeleteMapping("/delete/{addressId}")
    public ApiResponse deleteAddress(@PathVariable(value = "addressId") Long addressId){
        addressService.deleteAddress(addressId);
        return new SuccessResponse();
    }
}

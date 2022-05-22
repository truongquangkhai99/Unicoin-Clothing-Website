package com.unicoin.customer.controller;

import com.unicoin.customer.form.AddCustomerForm;
import com.unicoin.customer.resstresponse.ApiResponse;
import com.unicoin.customer.resstresponse.SuccessResponse;
import com.unicoin.customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/admin/customer",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ApiResponse viewCustomer(@RequestParam(value = "size", defaultValue = "10") Integer size,
                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "phoneNymber", required = false) String phoneNumber,
                                    @RequestParam(value = "fullName", required = false) String fullName,
                                    @RequestParam(value = "email", required = false) String email) {
        return new SuccessResponse(userService.viewCustomer(page, size, phoneNumber, fullName, email));
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@Valid @RequestBody AddCustomerForm addCustomerForm){
        userService.addCustomer(addCustomerForm);
        return new SuccessResponse();
    }

    @PostMapping("/login")
    public ApiResponse login() {
        return new SuccessResponse(userService.login());
    }

    @PutMapping("/update/{username}")
    public ApiResponse updateCustomer(@PathVariable("username") String username) {
        userService.updateCustomer(username);
        return new SuccessResponse();
    }

    @DeleteMapping("/{phoneNumber}")
    public ApiResponse deleteCustomer(@PathVariable("phoneNumber") String phoneNumber) {
        userService.uDeleteCustomer(phoneNumber);
        return new SuccessResponse();
    }
}

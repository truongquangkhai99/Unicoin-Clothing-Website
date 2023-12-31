package com.unicoin.customer.controller.admin;

import com.unicoin.customer.dto.JwtRequest;
import com.unicoin.customer.dto.UserDTO;
import com.unicoin.customer.form.AddCustomerForm;
import com.unicoin.customer.form.AddRoleForm;
import com.unicoin.customer.resstresponse.ApiResponse;
import com.unicoin.customer.resstresponse.SuccessResponse;
import com.unicoin.customer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/admin/customer")
@Slf4j
public class    UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ApiResponse viewCustomer(@RequestParam(value = "size", defaultValue = "10") Integer size,
                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
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
    public ApiResponse login(@RequestBody JwtRequest jwtRequest) {
        return new SuccessResponse(userService.login(jwtRequest));
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateCustomer(@PathVariable("id") Long id , @RequestBody AddCustomerForm addCustomerForm) {
        userService.updateCustomer(id , addCustomerForm);
        return new SuccessResponse();
    }

    @DeleteMapping("/{phoneNumber}")
    public ApiResponse deleteCustomer(@PathVariable("phoneNumber") String phoneNumber) {
        userService.uDeleteCustomer(phoneNumber);
        return new SuccessResponse();
    }

    @GetMapping("/getRoles")
    public ApiResponse getRoles(){
        return new SuccessResponse(userService.getRoles());
    }

    @PostMapping("/addRole")
    public ApiResponse addRole(AddRoleForm roleForm){
        userService.addRole(roleForm);
        return new SuccessResponse();
    }
}

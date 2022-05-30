package com.unicoin.customer.service;

import com.unicoin.customer.common.JwtResponse;
import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.entity.User;
import com.unicoin.customer.form.AddCustomerForm;

public interface UserService {
    RestResponsePage<User> viewCustomer(Integer page, Integer size, String phoneNumber, String fullName, String email);

    void addCustomer(AddCustomerForm addCustomerForm);

    JwtResponse login();

    void updateCustomer(Integer id  , AddCustomerForm addCustomerForm);

    void uDeleteCustomer(String phoneNumer);
}

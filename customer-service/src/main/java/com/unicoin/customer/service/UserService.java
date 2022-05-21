package com.unicoin.customer.service;

import com.unicoin.customer.common.JwtResponse;
import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.entity.User;

public interface UserService {
    RestResponsePage<User> viewCustomer();

    void addCustomer();

    JwtResponse login();

    void updateCustomer(String username);

    void deleteCustomer(String phoneNumer);
}

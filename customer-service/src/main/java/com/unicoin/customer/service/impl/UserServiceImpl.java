package com.unicoin.customer.service.impl;

import com.unicoin.customer.common.JwtResponse;
import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.entity.User;
import com.unicoin.customer.repository.Userrepository;
import com.unicoin.customer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    Userrepository userrepository;

    @Override
    public RestResponsePage<User> viewCustomer() {
        return null;
    }

    @Override
    public void addCustomer() {

    }

    @Override
    public JwtResponse login() {
        return null;
    }

    @Override
    public void updateCustomer(String username) {

    }

    @Override
    public void deleteCustomer(String phoneNumer) {

    }
}

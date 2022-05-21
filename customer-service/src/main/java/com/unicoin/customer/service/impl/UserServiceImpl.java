package com.unicoin.customer.service.impl;

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

}

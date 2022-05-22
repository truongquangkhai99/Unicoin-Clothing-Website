package com.unicoin.customer.service.impl;

import com.unicoin.customer.common.JwtResponse;
import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.entity.User;
import com.unicoin.customer.ex.AppException;
import com.unicoin.customer.ex.ExceptionCode;
import com.unicoin.customer.form.AddCustomerForm;
import com.unicoin.customer.repository.UserRepository;
import com.unicoin.customer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userrepository;

    @Override
    public RestResponsePage<User> viewCustomer() {
        return null;
    }

    @Override
    public void addCustomer(AddCustomerForm addCustomerForm) {
        log.info("start addCustomer");
        Optional<User> checkPhone = userrepository.findByPhoneNumber(addCustomerForm.getPhoneNumber());
        if(checkPhone.isPresent()){
            throw  new AppException(ExceptionCode.NOT_EXIT);
        }
        Optional<User> checkEmail = userrepository.findByEmail(addCustomerForm.getEmail());
        if(checkEmail.isPresent()){
            throw  new AppException(ExceptionCode.NOT_EXIT);
        }
        User user = new User();
        BeanUtils.copyProperties(addCustomerForm,user);
        user.setRegistStamp(new Timestamp(new Date().getTime()));
        user.setUpdateStamp(new Timestamp(new Date().getTime()));
        user.setStatus(true);
        userrepository.save(user);
        log.info("add Customer end");
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

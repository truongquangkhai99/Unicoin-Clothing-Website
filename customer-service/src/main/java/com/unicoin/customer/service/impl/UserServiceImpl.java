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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public RestResponsePage<User> viewCustomer(Integer page, Integer size, String phoneNumber, String fullName, String email) {
        log.info("Start viewCustomer");
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "fullName").and(Sort.by(Sort.Direction.ASC, "registStamp")));
        Page<User> userPage = userRepository.searchAllCustomer(phoneNumber, fullName, email, pageable);
        if (userPage.isEmpty()) throw new AppException(ExceptionCode.NOTFOUND_CUSTOMERS);
        log.info("End viewCustomer");
        return new RestResponsePage<>(userPage.toList(), page, size, userPage.getTotalElements(), userPage.getTotalPages());
    }

    @Override
    public void addCustomer(AddCustomerForm addCustomerForm) {
        log.info("start addCustomer");
        Optional<User> checkPhone = userRepository.findByPhoneNumber(addCustomerForm.getPhoneNumber());
        if(checkPhone.isPresent()){
            throw  new AppException(ExceptionCode.NOT_EXIT);
        }
        Optional<User> checkEmail = userRepository.findByEmail(addCustomerForm.getEmail());
        if(checkEmail.isPresent()){
            throw  new AppException(ExceptionCode.NOT_EXIT);
        }
        User user = new User();
        BeanUtils.copyProperties(addCustomerForm,user);
        user.setRegistStamp(new Timestamp(new Date().getTime()));
        user.setUpdateStamp(new Timestamp(new Date().getTime()));
        user.setStatus(true);
        userRepository.save(user);
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
    public void uDeleteCustomer(String phoneNumber) {
        log.info("Start deleteCustomer: phoneNumber {}", phoneNumber);
        Optional<User> optional = userRepository.findByPhoneNumber(phoneNumber);
        if (optional.isEmpty()) throw  new AppException(ExceptionCode.PHONENUMBER_IS_NOT_REGISTER);
        User user = new User();
        BeanUtils.copyProperties(optional.get(), user);
        user.setStatus(!user.getStatus());
        user.setUpdateStamp(new Timestamp(new Date().getTime()));
        userRepository.save(user);
        log.info("End deleteCustomer: phoneNumber {}", phoneNumber);
    }
}

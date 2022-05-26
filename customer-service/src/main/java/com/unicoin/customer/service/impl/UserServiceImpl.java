package com.unicoin.customer.service.impl;

import com.unicoin.customer.common.JwtResponse;
import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.dto.UserDTO;
import com.unicoin.customer.entity.User;
import com.unicoin.customer.ex.AppException;
import com.unicoin.customer.ex.ExceptionCode;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public RestResponsePage<UserDTO> viewCustomer(Integer page, Integer size, String phoneNumber, String fullName, String email) {
        log.info("Start viewCustomer");
        Pageable pageable = PageRequest.of(page -1 , size, Sort.by(Sort.Direction.ASC, "fullName").and(Sort.by(Sort.Direction.ASC, "registStamp")));
        Page<User> userPage = userRepository.searchAllCustomer(phoneNumber, fullName, email, pageable);
        if (userPage.isEmpty()) throw new AppException(ExceptionCode.NOTFOUND_CUSTOMERS);
        List<UserDTO> dtoList = userPage.toList().stream().map(item ->
                UserDTO.builder()
                        .id(item.getId())
                        .fullName(item.getFullName())
                        .phoneNumber(item.getPhoneNumber())
                        .address(item.getAddress())
                        .registStamp(item.getRegistStamp())
                        .updateStamp(item.getUpdateStamp())
                        .address(item.getAddress())
                        .email(item.getEmail())
                        .status(item.getStatus())
                        .build())
                .collect(Collectors.toList());
        log.info("End viewCustomer");
        return new RestResponsePage<>(dtoList, page, size, userPage.getTotalElements(), userPage.getTotalPages());
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
    public void uDeleteCustomer(String phoneNumber) {
        log.info("Start uDeleteCustomer: phoneNumber {}", phoneNumber);
        Optional<User> optional = userRepository.findByPhoneNumber(phoneNumber);
        if (optional.isEmpty()) throw  new AppException(ExceptionCode.PHONENUMBER_IS_NOT_REGISTER);
        User user = new User();
        BeanUtils.copyProperties(optional.get(), user);
        user.setStatus(!user.getStatus());
        user.setUpdateStamp(new Timestamp(new Date().getTime()));
        userRepository.save(user);
        log.info("End uDeleteCustomer: phoneNumber {}", phoneNumber);
    }
}

package com.unicoin.customer.service.impl;

import com.unicoin.customer.common.JwtResponse;
import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.dto.UserDTO;
import com.unicoin.customer.entity.Role;
import com.unicoin.customer.entity.User;
import com.unicoin.customer.ex.AppException;
import com.unicoin.customer.ex.ExceptionCode;
import com.unicoin.customer.form.AddCustomerForm;
import com.unicoin.customer.form.AddRoleForm;
import com.unicoin.customer.repository.RoleRepository;
import com.unicoin.customer.repository.UserRepository;
import com.unicoin.customer.repository.UserRoleRepository;
import com.unicoin.customer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;


    private  PasswordEncoder passwordEncoder;

    @Override
    public RestResponsePage<UserDTO> viewCustomer(Integer page, Integer size, String phoneNumber, String fullName, String email) {
        log.info("Start viewCustomer");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "fullName").and(Sort.by(Sort.Direction.ASC, "registStamp")));
        Page<User> userPage = userRepository.searchAllCustomer(phoneNumber, fullName, email, pageable);
        if (userPage.isEmpty()) throw new AppException(ExceptionCode.NOTFOUND_CUSTOMERS);
        List<UserDTO> dtoList = userPage.toList().stream().map(item ->
                        UserDTO.builder()
                                .id(item.getId())
                                .fullName(item.getFullName())
                                .phoneNumber(item.getPhoneNumber())
                                .registStamp(item.getRegistStamp())
                                .email(item.getEmail())
                                .status(item.getStatus())
                                .build())
                .collect(Collectors.toList());
        log.info("End viewCustomer");
        return new RestResponsePage<>(dtoList, page, size, userPage.getTotalElements(), userPage.getTotalPages());
    }

    @Override
    public void addCustomer(AddCustomerForm addCustomerForm) {
        log.info("start addCustomer");
        Optional<User> checkPhone = userRepository.findByPhoneNumber(addCustomerForm.getPhoneNumber());
        if (checkPhone.isPresent()) {
            throw new AppException(ExceptionCode.PHONENUMBER_ALREADY_EXIST);
        }
        Optional<User> checkEmail = userRepository.findByEmail(addCustomerForm.getEmail());
        if (checkEmail.isPresent()) {
            throw new AppException(ExceptionCode.EMAIL_ALREADY_EXIST);
        }
        User user = new User();
        BeanUtils.copyProperties(addCustomerForm, user);
        user.setPassword(passwordEncoder.encode(addCustomerForm.getPassword()));
        user.setRegistStamp(new Timestamp(new Date().getTime()));
        user.setStatus(true);
        userRepository.save(user);
        log.info("add Customer end");
    }

    @Override
    public JwtResponse login() {
        return null;
    }

    @Override
    public void updateCustomer(Long id, UserDTO userDTO) {
        log.info("start update customer");
        Optional<User> checkId = userRepository.findById(id);
        if (checkId.isPresent()) {
            User user = checkId.get();
            user.setEmail(userDTO.getEmail());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setFullName(userDTO.getFullName());
            userRepository.save(user);
            log.info("end update customer");
        } else {
            throw new AppException(ExceptionCode.VALID_ID);
        }
    }

    @Override
    public void uDeleteCustomer(String phoneNumber) {
        log.info("Start uDeleteCustomer: phoneNumber {}", phoneNumber);
        Optional<User> optional = userRepository.findByPhoneNumber(phoneNumber);
        if (optional.isEmpty())
            throw new AppException(ExceptionCode.PHONENUMBER_IS_NOT_REGISTER);
        User user = new User();
        BeanUtils.copyProperties(optional.get(), user);
        user.setStatus(!user.getStatus());
        userRepository.save(user);
        log.info("End uDeleteCustomer: phoneNumber {}", phoneNumber);
    }

    @Override
    public RestResponsePage<Role> getRoles() {
        log.info("Start getRoles");
        List<Role> listRoles = roleRepository.findAllByStatus(true);
        log.info("End getRoles");
        return new RestResponsePage<>(listRoles);
    }

    @Override
    public void addRole(AddRoleForm roleForm) {
        log.info("Start addRole");
        Role role = Role.builder()
                .roleName(roleForm.getRoleName())
                .memo(roleForm.getMemo())
                .status(true)
                .build();
        roleRepository.save(role);
        log.info("End addRole");
    }


}

package com.unicoin.customer.service;

import com.google.common.base.Optional;
import com.unicoin.customer.common.JwtResponse;
import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.dto.UserDTO;
import com.unicoin.customer.entity.Role;
import com.unicoin.customer.entity.User;
import com.unicoin.customer.form.AddCustomerForm;
import com.unicoin.customer.form.AddRoleForm;

public interface UserService {
    RestResponsePage<UserDTO> viewCustomer(Integer page, Integer size, String phoneNumber, String fullName, String email);

    void addCustomer(AddCustomerForm addCustomerForm);

    JwtResponse login();

    void updateCustomer(Long id  , AddCustomerForm addCustomerForm);

    void uDeleteCustomer(String phoneNumer);

    RestResponsePage<Role> getRoles();

    void addRole(AddRoleForm roleForm);
}

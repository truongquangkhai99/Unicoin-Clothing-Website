package com.unicoin.customer.service;

import com.unicoin.clients.form.customerform.CustomerDemoForm;

public interface CustomerDemoService {
    void registrationCustomer(CustomerDemoForm customerDemoForm);

    boolean checkCustomer(String customerFistName);
}

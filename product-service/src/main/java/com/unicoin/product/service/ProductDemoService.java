package com.unicoin.product.service;

import com.unicoin.product.entity.ProductDemo;
import com.unicoin.clients.form.productform.ProductDemoForm;

public interface ProductDemoService {

    ProductDemo addProduct(ProductDemoForm productDemoForm);

    void sendAddOrder();

    void sendUpdateOrder();
}

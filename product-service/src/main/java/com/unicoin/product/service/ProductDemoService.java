package com.unicoin.product.service;

import com.unicoin.clients.form.productform.ProductDemoForm;
import com.unicoin.product.entity.ProductDemo;

public interface ProductDemoService {

    ProductDemo addProduct(ProductDemoForm productDemoForm);

    void sendAddOrder();

    void sendUpdateOrder();
}

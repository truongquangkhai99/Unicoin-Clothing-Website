package com.unicoin.clients.form.productform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDemoForm {

    private String productName;
    private double price;
    private String customerFistName;
}

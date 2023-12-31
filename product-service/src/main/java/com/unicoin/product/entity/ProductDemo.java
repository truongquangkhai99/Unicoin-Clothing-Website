package com.unicoin.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDemo {
    Long id;
    private String productName;
    private double price;
    private String customerFistName;
}

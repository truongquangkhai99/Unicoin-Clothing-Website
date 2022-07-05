package com.unicoin.product.form;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddExportOrderDetail {
    private Integer variantId;
    private Integer quantity;
    private Double price;
    private Long exportOrderId;
}

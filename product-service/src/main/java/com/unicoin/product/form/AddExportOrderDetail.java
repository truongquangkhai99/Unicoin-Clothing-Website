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
    private Long variantId;
    private String variantName;
    private Integer quantity;
    private Long price;
    private Long exportOrderId;
}

package com.unicoin.product.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdatePriceForm {
    private Long variantId;
    private Long price;
    private Integer qty;
}

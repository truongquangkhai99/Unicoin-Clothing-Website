package com.unicoin.product.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddImportOrderDetail {
    private Integer varianId;

    private Integer quantity;

    private Integer cost;
}

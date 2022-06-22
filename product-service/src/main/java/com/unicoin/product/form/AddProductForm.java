package com.unicoin.product.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductForm {

    private String productName;

    private String productCode;

    private Long supplier;

    private Integer status;

}

package com.unicoin.product.form;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValueForm {
    private String value;
    private Integer status;
}

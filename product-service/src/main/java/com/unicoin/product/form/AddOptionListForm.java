package com.unicoin.product.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddOptionListForm {
    private String optionCode;
    private String optionName;
}

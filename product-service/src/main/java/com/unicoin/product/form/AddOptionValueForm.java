package com.unicoin.product.form;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddOptionValueForm {
    private Long productId;
    private OptionForm option;
    private List<ValueForm> optionValue;

    @Data
    public class OptionForm{
        private String optionName;
        private String optionCode;
    }
}

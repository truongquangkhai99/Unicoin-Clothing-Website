package com.unicoin.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OptionVariantDTO {
    private Long optionId;
    private String optionName;
    private String optionCode;
    private Long optionValueId;
    private String optionValue;
}
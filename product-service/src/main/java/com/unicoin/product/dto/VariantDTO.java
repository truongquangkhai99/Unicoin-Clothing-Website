package com.unicoin.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantDTO {
    private Long variantId;
    private String skuID;
    private Long productId;
    private String productName;
    private Integer qty;
    private Long price;
    private List<OptionVariantDTO> option;
}

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
    private String variantName;
    private Long productId;
    private String productName;
    private Integer qty;
    private Long price;
    private Long priceDiscount;
    private String status;
    private List<OptionVariantDTO> option;
}

package com.unicoin.product.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductShopDTO {
    private Long productId;
    private SupplierDTO supplier;
    private List<VariantDTO> variantList;
    private Long priceMin;
    private Long priceMax;
}

package com.unicoin.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExportOrderDetailDTO {

    private Long id;

    private Integer variantId;

    private String variantName;

    private Integer quantity;

    private Long price;

    private Long orderId;
}

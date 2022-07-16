package com.unicoin.order.DTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class ExportOrderDetailDTO {
    private Long id;
    private Long variantId;
    private Integer quantity;
    private Long price;
    private Long exportOrderId;
}

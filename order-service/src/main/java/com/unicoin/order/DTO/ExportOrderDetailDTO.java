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
    private Integer variantId;
    private Integer quantity;
    private Double price;
    private Long exportOrderId;
}

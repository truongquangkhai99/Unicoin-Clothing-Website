package com.unicoin.order.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ImportOrderDetailDTO {
    private Long id ;
    private Integer variantId;
    private Integer quantity;
    private Integer cost;
    private Long importOrdersId ;
}

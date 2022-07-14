package com.unicoin.order.form;

import com.unicoin.order.entity.ExportOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormExportOrderDetail {
    private Long id;
    private Long variantId;
    private Integer quantity;
    private Long price;
    private Long exportOrderId;
}

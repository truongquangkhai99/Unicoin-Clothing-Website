package com.unicoin.clients.rabbitmqModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QueueExportOrderDetail {

    private Long id;

    private Integer variantId;

    private Integer quantity;

    private Double price;
}
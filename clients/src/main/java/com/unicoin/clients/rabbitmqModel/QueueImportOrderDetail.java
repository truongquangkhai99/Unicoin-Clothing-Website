package com.unicoin.clients.rabbitmqModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QueueImportOrderDetail {

    private Long id;

    private Integer variantId;

    private Integer quantity;

    private Integer cost;

}

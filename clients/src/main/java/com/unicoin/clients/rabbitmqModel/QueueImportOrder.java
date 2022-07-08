package com.unicoin.clients.rabbitmqModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QueueImportOrder {
    private Long id;

    private Long userId;

    private Timestamp registStamp;

    private Integer status;

    private List<QueueImportOrderDetail> queueImportOrderDetails;
}

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
public class QueueExportOrder {

    private Long id;

    private Integer usedId;

    private String nameRecipient;

    private String phoneRecipient;

    private String address;

    private Timestamp registStamp;

    private Integer status;

    private List<QueueExportOrderDetail> queueExportOrderDetails;

}
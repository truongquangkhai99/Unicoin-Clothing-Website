package com.unicoin.order.form;

import com.unicoin.order.entity.ExportOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AddExportOrders {
    private Long id;
    private Integer usedId;
    private String nameRecipient;
    private String phoneRecipient;
    private String address;
    private Instant registStamp;
    private Integer status;
    List<FormExportOrderDetail> data;


}

package com.unicoin.order.form;

import com.unicoin.order.entity.ExportOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AddExportOrders {
    private Long id;
    private String userPhoneNumber;
    private String nameRecipient;
    private String phoneRecipient;
    private String address;
    private Timestamp registStamp;
    private Integer status;
    private List<FormExportOrderDetail> data;


}

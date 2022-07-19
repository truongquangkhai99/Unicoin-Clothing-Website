package com.unicoin.order.DTO;

import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExportOrderDTO {
    private Long id;
    private String userPhoneNumber;
    private String nameRecipient;
    private String phoneRecipient;
    private String address;
    private Integer orderType;
    private Timestamp registStamp;
    private Integer status;
}

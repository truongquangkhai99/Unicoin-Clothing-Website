package com.unicoin.order.DTO;

import lombok.*;

import java.time.Instant;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExportOrderDTO {
    private Long id;
    private Integer usedId;
    private String nameRecipient;
    private String phoneRecipient;
    private String address;
    private Instant registStamp;
    private Integer status;
}

package com.unicoin.order.DTO;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ImportOrderDTO {
    private Long id;
    private String userPhoneNumber;
    private Timestamp registStamp;
    private Integer status;
}

package com.unicoin.order.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportOrdersDTO {
    private Long id ;

    private Long userId;

    private Timestamp registStamp;

    private Integer status;
}

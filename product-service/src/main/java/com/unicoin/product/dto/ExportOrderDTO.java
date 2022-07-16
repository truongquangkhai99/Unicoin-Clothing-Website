package com.unicoin.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class ExportOrderDTO {
    private Long id;

    private String userPhoneNumber;

    private String nameRecipient;

    private String phoneRecipient;

    private String address;

    private Integer status;
}

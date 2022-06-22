package com.unicoin.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class SupplierDTO {
    private Long supplierId;
    private String supplierName;
    private String supplierCode;
    private String phoneNumber;
    private String email;
    private String memo;
    private String address;
}

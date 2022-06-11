package com.unicoin.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressDTO {
    private Long addressId;
    private String line;
    private Boolean status;
    private Timestamp registStamp;
}

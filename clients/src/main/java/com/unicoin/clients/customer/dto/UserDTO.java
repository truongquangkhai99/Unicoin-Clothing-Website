package com.unicoin.clients.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO implements Serializable {

    private Long id;

    private String phoneNumber;

    private String fullName;

    private String email;

    private Timestamp registStamp;

    private Timestamp updateStamp;

    private Boolean status;
}

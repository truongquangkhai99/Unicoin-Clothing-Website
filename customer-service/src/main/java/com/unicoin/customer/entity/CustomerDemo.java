package com.unicoin.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDemo {

    private Long customerId;
    private String fistName;
    private String lastName;
    private String email;
}

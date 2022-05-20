package com.unicoin.clients.form.customerform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDemoForm {
    private Long customerId;
    private String fistName;
    private String lastName;
    private String email;
}

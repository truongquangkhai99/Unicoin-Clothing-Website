package com.unicoin.customer.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;
}

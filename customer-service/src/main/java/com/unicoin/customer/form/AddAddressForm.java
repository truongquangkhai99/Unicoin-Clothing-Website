package com.unicoin.customer.form;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
@Getter
@Setter
public class AddAddressForm {
    @NotNull(message = "line is not null")
    private String line;

    @NotNull(message = "userId is not null")
    private Long userId;
}

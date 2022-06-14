package com.unicoin.customer.form;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
@Getter
@Setter
public class AddCustomerForm {
    @NotNull(message = "phoneNumber is not null")
    @NotBlank(message = "phoneNumber is notBlank")
    @Pattern(message = "PhoneNumber is not valid", regexp = "^0[0-9]{9}")
    private String phoneNumber;

    @NotNull(message = "fullname is notnull")
    private String fullName;

    @NotNull(message = "emaill is notnull")
    @Email(message = "emaill is not valid")
    private String email;

    @NotBlank(message = "password is notBlank")
    @NotNull(message = "password is notnull")
    private String password;

}

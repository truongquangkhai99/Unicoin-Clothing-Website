package com.unicoin.product.form;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddSupplierForm {
    private String supplierName;
    private String supplierCode;
    private String phoneNumber;
    private String email;
    private String memo;
    private String address;
    private Long updateUser;
}

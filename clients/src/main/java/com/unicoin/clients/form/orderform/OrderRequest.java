package com.unicoin.clients.form.orderform;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String customerFistName;
    private Date createAt;
}

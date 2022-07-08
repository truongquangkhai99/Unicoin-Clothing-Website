package com.unicoin.product.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CheckoutExportOrders {
    private Long id;
    private String nameRecipient;
    private String phoneRecipient;
    private String address;
}

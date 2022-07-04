package com.unicoin.product.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AddExportOrders {
    private Integer usedId;
    private String nameRecipient;
    private String phoneRecipient;
    private String address;
}

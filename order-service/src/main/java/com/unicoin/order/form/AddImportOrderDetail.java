package com.unicoin.order.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddImportOrderDetail {

    private Integer varianId;

    private Integer quantity;

    private Integer cost;

}

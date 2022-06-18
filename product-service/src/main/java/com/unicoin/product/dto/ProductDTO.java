package com.unicoin.product.dto;

import com.unicoin.product.entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    private String productName;

    private String productCode;

    private SupplierDTO supplier;

    private Timestamp registStamp;

    private Long updateUser;

    private Integer status;
}

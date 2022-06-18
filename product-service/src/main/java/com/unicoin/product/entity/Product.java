package com.unicoin.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", nullable = false)
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_CODE", length = 20)
    private String productCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SUPPLIER_ID", nullable = false)
    private Supplier supplier;

    @Column(name = "REGIST_STAMP", nullable = false)
    private Timestamp registStamp;

    @Column(name = "UPDATE_USER", nullable = false)
    private Long updateUser;

    @Column(name = "status")
    private Integer status;

}
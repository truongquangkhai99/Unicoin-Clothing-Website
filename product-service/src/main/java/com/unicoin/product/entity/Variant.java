package com.unicoin.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "variants")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VARIANT_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "SKU_ID")
    private String skuId;

    @Column(name = "QTY")
    private Integer qty;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "STATUS")
    private Integer status;

}
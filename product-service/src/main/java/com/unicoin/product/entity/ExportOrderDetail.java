package com.unicoin.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "export_order_detail")
public class ExportOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "export_order_detail_id", nullable = false)
    private Long id;

    @Column(name = "variant_id")
    private Integer variantId;

    @Column(name = "variant_name")
    private String variantName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Long price;

    @Column(name = "PRICE_DISCOUNT")
    private Long priceDiscount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "export_order_id", nullable = false)
    private ExportOrder exportOrderId;


}
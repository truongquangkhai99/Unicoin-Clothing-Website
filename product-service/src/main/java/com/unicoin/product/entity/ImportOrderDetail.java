package com.unicoin.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "import_order_detail")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMPORT_ORDER_DETAIL_ID" , nullable = false)
    private Long id ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VARIANT_ID" , nullable = false)
    private Variant variantId;

    @Column(name = "QUANTITY" , nullable = false)
    private Integer quantity;

    @Column(name = "PRICE" , nullable = false)
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IMPORT_ORDER_ID", nullable = false)
    private ImportOrders importOrdersId ;
}

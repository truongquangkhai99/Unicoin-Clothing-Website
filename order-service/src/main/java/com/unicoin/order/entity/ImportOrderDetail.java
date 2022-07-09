package com.unicoin.order.entity;

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

    @Column(name = "VARIANT_ID" , nullable = false)
    private Long variantId;

    @Column(name = "QUANTITY" , nullable = false)
    private Integer quantity;

    @Column(name = "COST" , nullable = false)
    private Long cost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IMPORT_ORDER_ID", nullable = false)
    private ImportOrders importOrdersId ;
}

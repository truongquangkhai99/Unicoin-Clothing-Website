package com.unicoin.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "export_orders")
public class ExportOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_issue_id", nullable = false)
    private Long id;

    @Column(name = "used_id")
    private Integer usedId;

    @Column(name = "name_recipient", length = 45)
    private String nameRecipient;

    @Column(name = "phone_recipient", length = 45)
    private String phoneRecipient;

    @Column(name = "address")
    private String address;

    @Column(name = "regist_stamp")
    private Instant registStamp;

    @Column(name = "status")
    private Integer status;



}
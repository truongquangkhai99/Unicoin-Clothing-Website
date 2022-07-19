package com.unicoin.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
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
    @Column(name = "export_order_id", nullable = false)
    private Long id;

    @Column(name = "USER_PHONE_NUMBER")
    private String userPhoneNumber;

    @Column(name = "name_recipient", length = 45)
    private String nameRecipient;

    @Column(name = "phone_recipient", length = 45)
    private String phoneRecipient;

    @Column(name = "address")
    private String address;

    @Column(name = "order_type")
    private Integer orderType;

    @Column(name = "regist_stamp")
    private Timestamp registStamp;

    @Column(name = "status")
    private Integer status;



}
package com.unicoin.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "suppliers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIER_ID", nullable = false)
    private Long id;

    @Column(name = "SUPPLIER_CODE", length = 10)
    private String supplierCode;

    @Column(name = "SUPPLIER_NAME")
    private String supplierName;

    @Column(name = "PHONE_NUMBER", length = 15)
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MEMO")
    private String memo;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "REGIST_STAMP", nullable = false)
    private Timestamp registStamp;

    @Column(name = "UPDATE_STAMP", nullable = false)
    private Timestamp updateStamp;

    @Column(name = "UPDATE_USER")
    private Long updateUser;

    @Column(name = "status")
    private Integer status;
}
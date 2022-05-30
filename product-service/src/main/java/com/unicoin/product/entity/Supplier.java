package com.unicoin.product.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIER_ID", nullable = false)
    private Integer id;

    @Column(name = "SUPPLIER_NAME", nullable = false)
    private String supplierName;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "MEMO")
    private String memo;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "REGIST_STAMP", nullable = false)
    private Instant registStamp;

    @Column(name = "UPDATE_STAMP", nullable = false)
    private Instant updateStamp;

    @Column(name = "UPDATE_USER", nullable = false)
    private Integer updateUser;

    @Column(name = "status", nullable = false)
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateStamp() {
        return updateStamp;
    }

    public void setUpdateStamp(Instant updateStamp) {
        this.updateStamp = updateStamp;
    }

    public Instant getRegistStamp() {
        return registStamp;
    }

    public void setRegistStamp(Instant registStamp) {
        this.registStamp = registStamp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
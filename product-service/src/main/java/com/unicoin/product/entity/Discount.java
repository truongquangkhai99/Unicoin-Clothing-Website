package com.unicoin.product.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id", nullable = false)
    private Integer id;

    @Column(name = "discount_name", nullable = false, length = 45)
    private String discountName;

    @Column(name = "percent", nullable = false)
    private Integer percent;

    @Column(name = "memo")
    private String memo;

    @Column(name = "regist_stamp", nullable = false)
    private Instant registStamp;

    @Column(name = "udpate_user", nullable = false, length = 45)
    private String udpateUser;

    @Column(name = "status", nullable = false)
    private Boolean status = false;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUdpateUser() {
        return udpateUser;
    }

    public void setUdpateUser(String udpateUser) {
        this.udpateUser = udpateUser;
    }

    public Instant getRegistStamp() {
        return registStamp;
    }

    public void setRegistStamp(Instant registStamp) {
        this.registStamp = registStamp;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
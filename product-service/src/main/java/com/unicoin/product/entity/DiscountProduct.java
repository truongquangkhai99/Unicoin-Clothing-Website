package com.unicoin.product.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "discount_product")
public class DiscountProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_product_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "discount_id", nullable = false)
    private Discount discount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "commodity_id", nullable = false)
    private Commodity commodity;

    @Column(name = "discount_start_date", nullable = false)
    private Instant discountStartDate;

    @Column(name = "discount_end_date", nullable = false)
    private Instant discountEndDate;

    @Column(name = "regist_stamp", nullable = false, length = 45)
    private String registStamp;

    @Column(name = "update_user", nullable = false, length = 45)
    private String updateUser;

    @Column(name = "status", nullable = false)
    private Boolean status = false;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getRegistStamp() {
        return registStamp;
    }

    public void setRegistStamp(String registStamp) {
        this.registStamp = registStamp;
    }

    public Instant getDiscountEndDate() {
        return discountEndDate;
    }

    public void setDiscountEndDate(Instant discountEndDate) {
        this.discountEndDate = discountEndDate;
    }

    public Instant getDiscountStartDate() {
        return discountStartDate;
    }

    public void setDiscountStartDate(Instant discountStartDate) {
        this.discountStartDate = discountStartDate;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
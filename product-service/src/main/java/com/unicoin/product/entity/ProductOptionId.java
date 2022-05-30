package com.unicoin.product.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductOptionId implements Serializable {
    private static final long serialVersionUID = -8941261719668445982L;
    @Column(name = "OPTION_ID", nullable = false)
    private Integer optionId;
    @Column(name = "PRODUCT_ID", nullable = false)
    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, optionId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductOptionId entity = (ProductOptionId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.optionId, entity.optionId);
    }
}
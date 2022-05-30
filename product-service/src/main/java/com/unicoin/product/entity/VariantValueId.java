package com.unicoin.product.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VariantValueId implements Serializable {
    private static final long serialVersionUID = -8542078316737666182L;
    @Column(name = "VARIANT_ID", nullable = false)
    private Integer variantId;
    @Column(name = "PRODUCT_ID", nullable = false)
    private Integer productId;
    @Column(name = "OPTION_ID", nullable = false)
    private Integer optionId;
    @Column(name = "OPTION_VALUE_ID", nullable = false)
    private Integer optionValueId;

    public Integer getOptionValueId() {
        return optionValueId;
    }

    public void setOptionValueId(Integer optionValueId) {
        this.optionValueId = optionValueId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, optionId, variantId, optionValueId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VariantValueId entity = (VariantValueId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.optionId, entity.optionId) &&
                Objects.equals(this.variantId, entity.variantId) &&
                Objects.equals(this.optionValueId, entity.optionValueId);
    }
}
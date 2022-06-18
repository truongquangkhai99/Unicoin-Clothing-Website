package com.unicoin.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VariantValueId implements Serializable {
    private static final long serialVersionUID = 6712930963698801382L;
    @Column(name = "VARIANT_ID", nullable = false)
    private Long variantId;
    @Column(name = "OPTION_ID", nullable = false)
    private Long optionId;


    @Override
    public int hashCode() {
        return Objects.hash(optionId, variantId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VariantValueId entity = (VariantValueId) o;
        return Objects.equals(this.optionId, entity.optionId) &&
                Objects.equals(this.variantId, entity.variantId);
    }
}
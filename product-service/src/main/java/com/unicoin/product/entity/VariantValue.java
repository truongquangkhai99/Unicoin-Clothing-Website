package com.unicoin.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "variant_values")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VariantValue {
    @EmbeddedId
    private VariantValueId id;

    @MapsId("variantId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VARIANT_ID", nullable = false)
    private Variant variant;

    @MapsId("optionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OPTION_ID", nullable = false)
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OPTION_VALUE_ID", nullable = false)
    private OptionValue optionValue;

}
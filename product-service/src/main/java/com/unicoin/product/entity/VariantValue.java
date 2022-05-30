package com.unicoin.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "variant_values")
public class VariantValue {
    @EmbeddedId
    private VariantValueId id;

    @MapsId("variantId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VARIANT_ID", nullable = false)
    private Variant variant;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID", nullable = false),
            @JoinColumn(name = "OPTION_ID", referencedColumnName = "OPTION_ID", nullable = false)
    })
    private ProductOption productOptions;

    @MapsId("optionValueId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OPTION_VALUE_ID", nullable = false)
    private OptionValue optionValue;

    public OptionValue getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(OptionValue optionValue) {
        this.optionValue = optionValue;
    }

    public ProductOption getProductOptions() {
        return productOptions;
    }

    public void setProductOptions(ProductOption productOptions) {
        this.productOptions = productOptions;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public VariantValueId getId() {
        return id;
    }

    public void setId(VariantValueId id) {
        this.id = id;
    }
}
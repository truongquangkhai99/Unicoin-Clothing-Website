package com.unicoin.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_options")
public class ProductOption {
    @EmbeddedId
    private ProductOptionId id;

    @MapsId("optionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OPTION_ID", nullable = false)
    private Option option;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public ProductOptionId getId() {
        return id;
    }

    public void setId(ProductOptionId id) {
        this.id = id;
    }
}
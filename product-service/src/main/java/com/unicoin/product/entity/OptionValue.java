package com.unicoin.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "option_values")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OptionValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_VALUE_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OPTION_ID", nullable = false)
    private Option option;

    @Column(name = "OPTION_VALUE", length = 10000)
    private String optionValue;

    @Column(name = "STATUS")
    private Integer status;

}
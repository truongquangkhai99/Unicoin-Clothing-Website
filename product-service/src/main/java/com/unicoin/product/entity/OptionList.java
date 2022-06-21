package com.unicoin.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "option_list")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OptionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_ID", nullable = false)
    private Long id;

    @Column(name = "OPTION_CODE")
    private String optionCode;

    @Column(name = "OPTION_NAME")
    private String optionName;

}
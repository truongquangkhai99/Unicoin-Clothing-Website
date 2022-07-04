package com.unicoin.order.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Order {
    private Long id;
    private String customerFistName;
    private Date createAt;

}

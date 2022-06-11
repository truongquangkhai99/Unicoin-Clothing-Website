package com.unicoin.customer.dto;

import lombok.Data;

@Data
public class RoleDTO {
    private Integer id ;
    private String roleName;
    private String memo;
    Boolean status ;
}

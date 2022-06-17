package com.unicoin.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {
    private Integer id ;
    private String roleName;
    private String memo;
    Boolean status ;
}

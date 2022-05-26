package com.unicoin.customer.common;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@Builder
public class ClaimsContent {
    private List<String> authorities;
    private List<String> scope;
}

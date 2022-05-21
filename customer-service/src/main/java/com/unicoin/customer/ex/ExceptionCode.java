package com.unicoin.customer.ex;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    INVALID_VALUE(1000, "error1000"),
    NOTFOUND_CUSTOMER(1001, "error1001");

    private int status;
    private String error;
    ExceptionCode(int status, String error){
        this.status = status;
        this.error = error;
    }
}

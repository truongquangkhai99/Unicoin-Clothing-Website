package com.unicoin.product.ex;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    INVALID_VALUE(1000, "error1000");

    private int status;
    private String error;
    ExceptionCode(int status, String error){
        this.status = status;
        this.error = error;
    }
}

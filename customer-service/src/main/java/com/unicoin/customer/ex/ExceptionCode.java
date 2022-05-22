package com.unicoin.customer.ex;

import lombok.Getter;


@Getter
public enum ExceptionCode {
    INVALID_VALUE(1000, "error1000"),
    NOT_EXIT(1500, "error1500");

    private int status;
    private String error;
    ExceptionCode(int status, String error){
        this.status = status;
        this.error = error;
    }
}

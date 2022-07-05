package com.unicoin.order.ex;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    INVALID_VALUE(1000, "error1000"),
    EXPORTORDERS_NOT_EXIST(4000, "error4000"),
    INVALID_VALUE(1000, "error1000"),
    IMPORTORDERSID_NOT_EXIST(3000, "error3000");

    private int status;
    private String error;
    ExceptionCode(int status, String error){
        this.status = status;
        this.error = error;
    }
}

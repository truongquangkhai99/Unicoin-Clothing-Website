package com.unicoin.customer.ex;

import lombok.Getter;


@Getter
public enum ExceptionCode {
    INVALID_VALUE(1000, "error1000"),
    NOTFOUND_CUSTOMER(1001, "error1001"),
    NOTFOUND_CUSTOMERS(1002, "error1002"),
    PHONENUMBER_IS_NOT_REGISTER(1003, "error1003"),
    CHECK_PHONE(1500, "error1500"),
    CHECK_EMAIL(1501 ,"error1501"),
    VALID_ID(1502,"error1502");

    private int status;
    private String error;
    ExceptionCode(int status, String error){
        this.status = status;
        this.error = error;
    }
}

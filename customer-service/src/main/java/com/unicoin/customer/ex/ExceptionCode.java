package com.unicoin.customer.ex;

import lombok.Getter;


@Getter
public enum ExceptionCode {
    INVALID_VALUE(1000, "error1000"),
    NOTFOUND_CUSTOMER(1001, "error1001"),
    NOTFOUND_CUSTOMERS(1002, "error1002"),
    PHONENUMBER_IS_NOT_REGISTER(1003, "error1003"),
    PHONENUMBER_ALREADY_EXIST(1500, "error1500"),
    EMAIL_ALREADY_EXIST(1501 ,"error1501"),
    ROLE_ID_NOT_EXIST(1503, "error1503"),
    PASSWORD_NOT_NULL(1504,"error1504"),
    VALID_ID(1502,"error1502"),

    USER_ID_NOT_EXIST(1600,"error1600"),

    ADDRESS_ID_NOT_EXIST(1601,"error1601"),

    USERROLE_NOT_EXIST(1701,"error1701"),

    STATUS_IS_NOT_EXIST(1700,"error1700");

    private int status;
    private String error;
    ExceptionCode(int status, String error){
        this.status = status;
        this.error = error;
    }
}

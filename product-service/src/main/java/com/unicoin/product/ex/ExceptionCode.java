package com.unicoin.product.ex;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    INVALID_VALUE(1000, "error1000"),
    PRODUCT_IS_EXIST(2000, "error2000"),
    PRODUCT_IS_NOT_EXIST(2001, "error2001"),
    PRODUCTOPTION_IS_NOT_EXIST(2002, "error2002"),
    SUPPLIER_NOT_EXIST(2100, "error2100"),
    SUPPLIER_CODE_IS_EXIST(2101, "error2101"),
    OPTIONLIST_NAME_IS_EXIST(2200, "error2200"),
    OPTIONLIST_CODE_IS_EXIST(2201, "error2201"),
    OPTIONLIST_IS_NOT_EXIST(2202, "error2202"),
    OPTION_IS_NOT_EXIST(2203,"error2203"),
    OPTION_IS_USING_YOU_CANNOT_DELETE(2204, "error2204"),
    VARIANTVALUE_HAS_ADDED(2300, "error2300"),
    OPTIONVALUE_IS_NOT_EXIST(2400, "error2400"),
    OPTIONVALUE_IS_USING_YOU_CANNOT_DELETE(2401, "error2401");

    private int status;
    private String error;
    ExceptionCode(int status, String error){
        this.status = status;
        this.error = error;
    }
}

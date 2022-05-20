package com.unicoin.customer.ex;

import com.unicoin.customer.resstresponse.Translator;

public class AppException extends ResourceException{

    public AppException(ExceptionCode exceptionCode){
        super(exceptionCode.getStatus(), Translator.toLocale(exceptionCode.getError()));
    }

    public AppException(ExceptionCode exceptionCode, Object[] args){
        super(exceptionCode.getStatus(), Translator.toLocale(exceptionCode.getError(), args));
    }
}

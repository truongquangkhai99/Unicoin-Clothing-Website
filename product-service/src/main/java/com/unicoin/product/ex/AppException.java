package com.unicoin.product.ex;


import com.unicoin.product.resstresponse.Translator;

public class AppException extends ResourceException{

    public AppException(ExceptionCode exceptionCode){
        super(exceptionCode.getStatus(), Translator.toLocale(exceptionCode.getError()));
    }

    public AppException(ExceptionCode exceptionCode, Object[] args){
        super(exceptionCode.getStatus(), Translator.toLocale(exceptionCode.getError(), args));
    }
}

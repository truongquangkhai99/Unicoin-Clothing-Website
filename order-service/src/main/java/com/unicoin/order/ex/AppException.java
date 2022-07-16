package com.unicoin.order.ex;


import com.unicoin.order.restresponse.Translator;

public class AppException extends ResourceException{

    public AppException(ExceptionCode exceptionCode){
        super(exceptionCode.getStatus(), Translator.toLocale(exceptionCode.getError()));
    }

    public AppException(ExceptionCode exceptionCode, Object[] args){
        super(exceptionCode.getStatus(), Translator.toLocale(exceptionCode.getError(), args));
    }
}

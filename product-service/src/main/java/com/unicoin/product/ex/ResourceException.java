package com.unicoin.product.ex;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceException extends RuntimeException{
    private int status;
    private String error;

    public ResourceException(HttpStatus status){
        super(status.getReasonPhrase());
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }

    public ResourceException(int status, String error){
        super(error);
        this.status = status;
        this.error = error;
    }
}

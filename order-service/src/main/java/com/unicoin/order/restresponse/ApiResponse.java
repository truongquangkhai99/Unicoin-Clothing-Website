package com.unicoin.order.restresponse;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse extends ResponseEntity<ApiResponse.Payload> {

//    response Ok
    public ApiResponse(HttpStatus status) {
        super(new Payload(status.value(), status.getReasonPhrase(), null), status);
    }

    public ApiResponse(HttpStatus httpStatus, HttpHeaders httpHeaders){
        super(new Payload(httpStatus.value(), httpStatus.getReasonPhrase(), null), httpHeaders, HttpStatus.OK);
    }
    public ApiResponse(HttpStatus httpStatus, Object data){
        super(new Payload(httpStatus.value(), httpStatus.getReasonPhrase(), data), HttpStatus.OK);
    }

    public ApiResponse(int status, String message, HttpHeaders httpHeaders, Object data){
        super(new Payload(status, message, data), httpHeaders, HttpStatus.OK);
    }

    public ApiResponse(int status, String message, HttpHeaders httpHeaders){
        super(new Payload(status, message, null), httpHeaders, HttpStatus.OK);
    }



    @Value
    @AllArgsConstructor
    public static class Payload{
        private int status;
        private String error;
        private Object data;
    }
}

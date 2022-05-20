package com.unicoin.customer.resstresponse;

import org.springframework.http.HttpStatus;

public class SuccessResponse extends ApiResponse{
    public SuccessResponse() {
        super(HttpStatus.OK);
    }

    public SuccessResponse(Object data) {
        super(HttpStatus.OK, data);
    }
}

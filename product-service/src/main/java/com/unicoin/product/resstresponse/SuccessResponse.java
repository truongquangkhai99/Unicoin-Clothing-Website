package com.unicoin.product.resstresponse;

import com.unicoin.product.entity.ExportOrder;
import org.springframework.http.HttpStatus;

public class SuccessResponse extends ApiResponse{
    public SuccessResponse() {
        super(HttpStatus.OK);
    }

    public SuccessResponse(Object data) {
        super(HttpStatus.OK, data);
    }
}

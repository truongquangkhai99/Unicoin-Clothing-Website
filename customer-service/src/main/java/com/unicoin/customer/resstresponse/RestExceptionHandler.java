package com.unicoin.customer.resstresponse;

import com.unicoin.customer.ex.ResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler({ResourceException.class})
    public ApiResponse handleResourceException(ResourceException e){
      log.error(String.format("Uncaught exception : %s : %s ", e.getClass(), e.getMessage()), e);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ApiResponse(e.getStatus(), e.getMessage(), httpHeaders);
    }
}

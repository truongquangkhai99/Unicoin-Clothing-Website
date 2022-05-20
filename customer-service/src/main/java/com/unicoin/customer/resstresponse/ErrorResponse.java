package com.unicoin.customer.resstresponse;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ErrorResponse extends ApiResponse{

    public ErrorResponse(List<FieldError> errors, HttpHeaders httpHeaders){
        super(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), httpHeaders, new ApiError(errors.stream()
                .map(
                        e -> {
                            Optional<String> code = Arrays.stream(e.getCodes()).filter(
                                    c -> Translator.toLocale(c, e.getArguments()) != c).findFirst();
                            String strCode = code.isPresent() ? code.get() :  e.getCodes()[0];
                            return new Error(e.getField(), e.getRejectedValue(), Translator.toLocale(strCode, e.getArguments()));
                        }).collect(Collectors.toList())));
    }

    @Value
    @AllArgsConstructor
    public static  class ApiError{
        List<Error> errors;
    }

    @Value
    @AllArgsConstructor
    public static class Error{
        private String field;
        private Object value;
        private String message;
    }
}


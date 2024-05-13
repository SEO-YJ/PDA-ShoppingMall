package com.shoppingmall.shoppingmall.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResult<T> {
    boolean success;
    T response;
    ApiError error;
}

class ApiError {
    String message;
    HttpStatus httpStatus;
}

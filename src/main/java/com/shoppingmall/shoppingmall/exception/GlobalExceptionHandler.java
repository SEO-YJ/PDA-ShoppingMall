package com.shoppingmall.shoppingmall.exception;

import com.shoppingmall.shoppingmall.utils.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.shoppingmall.shoppingmall.utils.ApiUtils.error;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult<ApiUtils.ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return error( ApiUtils.ErrorResponse.of(ex), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiUtils.ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
//        System.out.println("여기까지는 출력됨!");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiUtils.ErrorResponse.of(ex));
//    }
}

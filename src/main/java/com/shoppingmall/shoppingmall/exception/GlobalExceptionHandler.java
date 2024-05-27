package com.shoppingmall.shoppingmall.exception;

import com.shoppingmall.shoppingmall.utils.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static com.shoppingmall.shoppingmall.utils.ApiUtils.error;

// 프로젝트의 모든 컨트롤러의 예외 처리를 전역으로 담당
// 다른 계층에서 터져도 throws 등을 통해 컨트롤러로 전달
// 결국 모든 프로젝트 코드 예외 처리를 도맡아 가능하다!
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult<ApiUtils.ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return error( ApiUtils.ErrorResponse.of(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateMemberIdException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiUtils.ApiResult<String> handleDuplicateMemberIdException(DuplicateMemberIdException error) {
        String errorMessage = error.getMessage();
        return error(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateProductNameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiUtils.ApiResult<String> handleDuplicateProductNameException(DuplicateProductNameException error) {
        String errorMessage = error.getMessage();
        return error(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PasswordNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult<String> handlePasswordNotValidException(PasswordNotValidException error) {
        String errorMessage = error.getMessage();
        return error(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiUtils.ApiResult<String> handleNoSuchElementException(NoSuchElementException error) {
        String errorMessage = error.getMessage();
        return error(errorMessage, HttpStatus.NOT_FOUND);
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiUtils.ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
//        System.out.println("여기까지는 출력됨!");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiUtils.ErrorResponse.of(ex));
//    }
}

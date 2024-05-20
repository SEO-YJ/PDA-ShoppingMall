package com.shoppingmall.shoppingmall.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

// TODO: 24.05.14까지 최신화
public class ApiUtils {
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(true, data, null);
    }

    public static <M> ApiResult<M> error(M message, HttpStatus httpStatus) {
        return new ApiResult<>(false,
                null,
                new ApiError<>(message, httpStatus));
    }

//    public static ApiResult validError(String message, HttpStatus httpStatus, ErrorResponse errorResponse) {
//        return new ApiResult(
//                false,
//                errorResponse,
//                new ApiError(message, httpStatus)
//        );
//    }

    @Getter
    @AllArgsConstructor
    public static class ApiResult<T> {
        boolean success;
        T response;
        ApiError error;
    }

    @Getter
    static class ApiError<M> {
        M message;
        HttpStatus httpStatus;

        ApiError(M message, HttpStatus httpStatus) {
            this.message = message;
            this.httpStatus = httpStatus;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ErrorResponse {

        private List<ErrorField> errors;

        public ErrorResponse(List<ErrorField> errors) {
            this.errors = errors;
        }

        public static ErrorResponse of(MethodArgumentNotValidException ex) {
            return new ErrorResponse(ErrorField.of(ex));
        }

        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        static class ErrorField {
            private String field;
            private String value;
            private String reason;

            public static List<ErrorField> of(MethodArgumentNotValidException ex) {
                return ex.getAllErrors().stream().map(error ->
                        new ErrorField(
                                ((FieldError) error).getField(),
                                ((FieldError) error).getRejectedValue() != null ? ((FieldError) error).getRejectedValue().toString() : "",
                                ((FieldError) error).getDefaultMessage()
                        )).collect(Collectors.toList());
            }
        }
    }
}


// TODO: ApiUtils 도전해보기 (24.05.14)
//public class ApiUtils<T> {
////    public static <T> ApiResult<T> success(T data) {
////        return new ApiResult<T>(data);
////    }
//
//    public static ApiResult error(String message, HttpStatus httpStatus) {
//        return new ApiResult<>(false, null, new ApiError(message, httpStatus));
//    }
//
//    @Getter
//    @AllArgsConstructor
//    static class ApiResult<T> {
//        // 1. 메서드로 내부 클래스 객체를 생성하여 반환
//        // 단점: ApiResult 타입이 객체화가 되야 메서드를 사용 가능
////    public ApiResult<T> success(T data) {
////        return new ApiResult<>(true, data, null);
////    }
//        boolean success;
//        T response;
//        ApiError error;
//    }
//
//    @Getter
//    static class ApiError {
//        String message;
//        HttpStatus httpStatus;
//
//        ApiError(String message, HttpStatus httpStatus) {
//            this.message = message;
//            this.httpStatus = httpStatus;
//        }
//    }
//}

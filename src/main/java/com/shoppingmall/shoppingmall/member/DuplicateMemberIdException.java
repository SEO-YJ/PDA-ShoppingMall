package com.shoppingmall.shoppingmall.member;

// TODO: 24.05.14까지 최신화

public class DuplicateMemberIdException extends RuntimeException {
    private String message;
    public DuplicateMemberIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

package com.shoppingmall.shoppingmall.exception;

/**
 * 기능: 중복 ID 검출시 발생시킬 사용자 정의 예외 클래스
 * 용도: 중복 ID를 검증하는 부분에서 에러 메세지를 발생시키기 위해 사용합니다.
 *
 * @author 최종 수정자: 서유준
 * @version 1.0, 작업 내용: 24.05.19 최신화
 * @see PasswordNotValidException#getMessage()
 */
public class PasswordNotValidException extends RuntimeException {
    /**
     * message: Error에 대한 메시지를 저장하는 필드입니다.
     */
    private String message;

    /**
     * message 멤버 필드를 초기화하는 생성자입니다.
     *
     * @param message 콘솔에 출력하기 위해 필요한 String 타입의 이름입니다.
     * @see PasswordNotValidException
     * @return None
     */
    public PasswordNotValidException(String message) {
        this.message = message;
    }

    /**
     * message 멤버 필드를 반환하는 메소드입니다.
     *
     * @see PasswordNotValidException
     * @return message 필드 초기화시 message 반환, 없으면 null을 반환합니다.
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}

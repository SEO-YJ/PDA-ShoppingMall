package com.shoppingmall.shoppingmall.member;

/**
 * 기능:
 * 용도
 *
 * @author 최종 수정자: 서유준
 * @version 1.0, 작업 내용: 24.05.16 최신화
 * @see
 * DuplicateMemberIdException#getMessage()
 */
public class DuplicateMemberIdException extends RuntimeException {
    /**
     *
     */
    private String message;

    /**
     * message 멤버 필드를 초기화하는 생성자입니다.
     *
     * @param message 콘솔에 출력하기 위해 필요한 String 타입의 이름입니다.
     * @see DuplicateMemberIdException
     * @return None
     */
    public DuplicateMemberIdException(String message) {
        this.message = message;
    }

    /**
     * message 멤버 필드를 반환하는 메소드입니다.
     *
     * @param None 콘솔에 출력하기 위해 필요한 String 타입의 이름입니다.
     * @see DuplicateMemberIdException
     * @return message 필드 초기화시 message 반환, 없으면 null을 반환합니다.
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}

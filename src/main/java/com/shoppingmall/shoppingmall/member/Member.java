package com.shoppingmall.shoppingmall.member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

/**
 * 기능: Member Entity 클래스
 * 용도: Member에 대한 Entity로 사용합니다.
 *
 * @author 최종 수정자: 서유준
 * @version 1.0, 작업 내용: 24.05.16 최신화
 * @see
 * Member#toString()
 */
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Member {
    /**
     * Member를 구분하기 위한 id 정보
     */
    private String userId;
    /**
     * Member의 비밀번호 정보
     */
    private String pw;
    /**
     * Member의 실제 이름 정보
     */
    private String name;
    /**
     * Member의 이메일 정보
     */
    private String email;
    /**
     * Member의 전화번호
     */
    private String contact;

    /**
     * message 멤버 필드를 반환하는 메소드입니다.
     *
     * @see Member
     * @return Member 객체의 멤버 필드를 출력합니다.
     */
    @Override
    public String toString() {
        return "Member{" +
                "userId='" + userId + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}

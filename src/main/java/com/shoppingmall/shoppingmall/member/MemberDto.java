package com.shoppingmall.shoppingmall.member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberDto {
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

    @Override
    public String toString() {
        return "MemberDTO{" +
                "userId='" + userId + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}

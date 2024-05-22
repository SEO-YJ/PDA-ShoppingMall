package com.shoppingmall.shoppingmall.member.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.shoppingmall.shoppingmall.member.entity.Member;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginReqDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Size(min = 6, max = 15, message = "아이디는 6 ~ 15자 이여야 합니다!")
    private String userId;
    /**
     * Member의 비밀번호 정보
     */
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8 ~ 15자 이여야 합니다!")
    private String pw;

    public Member convertToEntity() {
        return new Member(userId, pw);
    }

    @Override
    public String toString() {
        return "LoginReqDto{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
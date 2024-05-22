package com.shoppingmall.shoppingmall.member.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.shoppingmall.shoppingmall.member.entity.Member;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberDto {
    // MemberRequestDto, MemberResponseDto
    // SignUpReqDto, SignUpResDto
    // LoginReqDto, LoginResDto
    private int id;
    /**
     * Member를 구분하기 위한 id 정보
     */
    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Size(min = 6, max = 15, message = "아이디는 6 ~ 15자 이여야 합니다!")
    private String userId;
    /**
     * Member의 비밀번호 정보
     */
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8 ~ 15자 이여야 합니다!")
    private String pw;
    /**
     * Member의 실제 이름 정보
     */
    @NotBlank(message = "이름은 필수 입력입니다.")
    @Size(min = 1, max = 10, message = "이름은 1 ~ 10자 이여야 합니다!")
    private String name;
    /**
     * Member의 이메일 정보
     */
//    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Size(min = 6, max = 20, message = "이메일은 6 ~ 20자 이여야 합니다!")
    private String email;
    /**
     * Member의 전화번호
     */
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식에 맞지 않습니다.")
    @NotBlank(message = "연락처는 필수 입력입니다.")
    @Size(min = 12, max = 13, message = "전화번호는 12 ~ 13자 이여야 합니다!")
    private String contact;

    public Member convertToEntity() {
        return new Member(userId, pw, name, email, contact);
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}

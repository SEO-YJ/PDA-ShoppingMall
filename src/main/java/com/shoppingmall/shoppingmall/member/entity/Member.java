package com.shoppingmall.shoppingmall.member.entity;

import com.shoppingmall.shoppingmall.member.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 기능: Member Entity 클래스
 * 용도: Member에 대한 Entity로 사용합니다.
 *
 * @author 최종 수정자: 서유준
 * @version 1.0, 작업 내용: 24.05.19 최신화
 * @see Member#toString()
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
//@RequiredArgsConstructor
public class Member {
    /**
     * PK
     * Member를 DB에서 구분하기 위한 정보
     */
    @Id // 식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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


    public Member(String userId, String pw, String name, String email, String contact) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public Member(String userId, String pw) {
        this.userId = userId;
        this.pw = pw;
    }

    /**
     *  MemberDto를 전달 받아 Member로 변환하여 반환하는 메소드입니다.
     *
     * @see Member
     * @return Member 객체를 생성하여 반환합니다.
     */
//    public static Member fromDtoToEntity(MemberDto memberDto) {
//        return new Member(
//                memberDto.getUserId(),
//                memberDto.getPw(),
//                memberDto.getName(),
//                memberDto.getEmail(),
//                memberDto.getContact()
//        );
//    }

    public boolean checkPassword(String pw){
        return this.pw.equals(pw);
    }

    /**
     * message 멤버 필드를 반환하는 메소드입니다.
     *
     * @see Member
     * @return Member 객체의 멤버들을 문자열로 묶어 반환합니다.
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

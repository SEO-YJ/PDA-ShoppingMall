package com.shoppingmall.shoppingmall.member;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

// TODO: 24.05.14까지 최신화
@Service
@AllArgsConstructor
public class MemberService {
    MemberRepository memberRepository;

    public String join(Member member) {
        return memberRepository.save(member);
    }

    public boolean checkDuplicateId(String userId) {
        Member existMember = memberRepository.findById(userId);

        try {
            if(existMember != null) {
                throw new DuplicateMemberIdException("중복된 ID 입니다.");
            }
            return false;
        } catch (DuplicateMemberIdException e) {
            e.getMessage();
            return true;
        }
    }

// TODO: 아이디 중복 검증 만들어보기
//
//    public Boolean duplicate(String userId) {
//        return memberRepository.duplicate(userId);
//    }


// TODO: @JsonNaming Test
//
//      public Member join(Member member) {
//          return memberRepository.save(member);
//      }
}

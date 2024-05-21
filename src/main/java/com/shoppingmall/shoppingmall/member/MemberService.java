package com.shoppingmall.shoppingmall.member;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;

// TODO: 24.05.14까지 최신화
@Service
@AllArgsConstructor
@Slf4j
public class MemberService {
    MemberRepository memberRepository;

    public String join(MemberDto memberDto) {
        // Dto -> Entity 변환
        Member requestMember = memberDto.convertToEntity();
        return memberRepository.save(requestMember);
    }

    public boolean checkDuplicateId(String userId) {
        Member existMember = memberRepository.findById(userId);

        try {
            if(existMember != null) {
                throw new DuplicateMemberIdException("중복된 ID 입니다.");
            }
            return false;
        } catch (DuplicateMemberIdException e) {
            log.info(e.getMessage());
            return true;
        }
    }

    public Connection makeConnection() {
        return memberRepository.makeConnection();
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

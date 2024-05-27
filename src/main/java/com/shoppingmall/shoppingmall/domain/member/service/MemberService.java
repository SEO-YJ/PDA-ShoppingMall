package com.shoppingmall.shoppingmall.domain.member.service;

import com.shoppingmall.shoppingmall.domain.member.entity.Member;
import com.shoppingmall.shoppingmall.domain.member.dto.MemberLoginReq;
import com.shoppingmall.shoppingmall.domain.member.dto.MemberDto;

import com.shoppingmall.shoppingmall.domain.member.repository.MemberRepository;
import com.shoppingmall.shoppingmall.exception.PasswordNotValidException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

// TODO: 24.05.14까지 최신화
@Service
@AllArgsConstructor
@Slf4j
public class MemberService {
    MemberRepository memberRepository;

    @Transactional
    public String join(MemberDto memberDto) {
        Member requestMember = memberDto.convertToEntity();

        Member joinedMember = memberRepository.save(requestMember);
        return joinedMember.getUserId();
    }

    public boolean checkDuplicateId(MemberDto memberDto) {
        Member requestMember = memberDto.convertToEntity();

        Optional<Member> existMember = memberRepository.findByUserId(requestMember.getUserId());

        if(existMember.isPresent()) {
            log.info(existMember.get().getUserId());
            return true;
        } else {
            return false;
        }
    }

    public String login(MemberLoginReq memberLoginReq) {
        Member member = memberLoginReq.convertToEntity();
        /**
         * 1) userId로 DB를 조회
         * - 만약 없으면 id가 존재하지 않는다.
         * 2) pw 비교
         * - 만약 틀리면? 예외 "비밀번호가 틀렸습니다."
         * 3) 성공? 로그인 유저의 이름을 반환
         **/
        Member loginMember
                = memberRepository.findByUserId(member.getUserId())
                .orElseThrow(()
                -> new NoSuchElementException("아이디가 존재하지 않습니다."));

        if(isRightPassword(loginMember, member))
            return loginMember.getName();
        else
            throw new PasswordNotValidException("비밀번호가 일치하지 않습니다.");
    }

    private static boolean isRightPassword(Member loginMember, Member member) {
        return loginMember.getPw().equals(member.getPw());
    }

    public List<Member> getMembers() {
        return memberRepository.getMembers();
    }


// TODO: 아이디 중복 검증 만들어보기
//
//    public Connection makeConnection() {
//        return memberRepository.makeConnection();
//    }

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

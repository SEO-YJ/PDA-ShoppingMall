package com.shoppingmall.shoppingmall.member;

import com.shoppingmall.shoppingmall.member.dto.LoginReqDto;
import com.shoppingmall.shoppingmall.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Optional;

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

    public Member login(LoginReqDto loginReqDto) {
        Member member = loginReqDto.convertToEntity();
        Optional<Member> findMember = memberRepository.findByUserID(member.getUserId());
        if(!findMember.orElseThrow(()->new NotCorrespondingEmailException("해당 이메일이 존재하지 않습니다.")).checkPassword(member.getPw())){
            throw new IllegalStateException("이메일과 비밀번호가 일치하지 않습니다.");
        }
        return findMember.get();
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

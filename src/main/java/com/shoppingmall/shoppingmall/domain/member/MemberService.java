package com.shoppingmall.shoppingmall.domain.member;

import com.shoppingmall.shoppingmall.domain.member.entity.Member;
import com.shoppingmall.shoppingmall.domain.member.dto.LoginReqDto;
import com.shoppingmall.shoppingmall.domain.member.dto.MemberDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

// TODO: 24.05.14까지 최신화
@Service
@AllArgsConstructor
@Slf4j
public class MemberService {
    MemberRepository memberRepository;

    @Transactional
    public String join(MemberDto memberDto) {
        // Dto -> Entity 변환
        Member requestMember = memberDto.convertToEntity();
        memberRepository.save(requestMember);
        Member findMember = memberRepository.findByUserID(requestMember.getUserId());
//        if(!findMember.orElseThrow(()->new NotCorrespondingEmailException("해당 이메일이 존재하지 않습니다.")).checkPassword(requestMember.getPw())){
//            throw new IllegalStateException("이메일과 비밀번호가 일치하지 않습니다.");
//        }
        return findMember.getUserId();
    }

    public boolean checkDuplicateId(MemberDto memberDto) {
        Member requestMember = memberDto.convertToEntity();
        Member existMember = memberRepository.findById(requestMember.getId());

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

    public Member login(LoginReqDto loginReqDto) {
        Member member = loginReqDto.convertToEntity();
        Member findMember = memberRepository.findByUserID(member.getUserId());
//        if(!findMember.orElseThrow(()->new NotCorrespondingEmailException("해당 이메일이 존재하지 않습니다.")).checkPassword(member.getPw())){
//            throw new IllegalStateException("이메일과 비밀번호가 일치하지 않습니다.");
//        }
        return findMember;
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

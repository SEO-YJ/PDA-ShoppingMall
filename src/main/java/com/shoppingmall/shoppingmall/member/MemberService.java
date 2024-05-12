package com.shoppingmall.shoppingmall.member;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    MemberRepository memberRepository;

    public String join(Member member) {
        return memberRepository.save(member);
    }
}

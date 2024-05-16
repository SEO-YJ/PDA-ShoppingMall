package com.shoppingmall.shoppingmall.member;

import exercise.exception.InputBoundErrorException;
import org.springframework.stereotype.Repository;

import java.util.*;

// TODO: 24.05.14까지 최신화
@Repository
public class MemberRepository {
    private Map<String, Member> memberTable = new HashMap<>();

    public String save(Member member) {
        memberTable.put(member.getUserId(), member);
        Member savedMember = memberTable.get(member.getUserId());
        System.out.println("/users : repository - " + savedMember);
        return savedMember.getUserId();
    }

    public Member findById(String userId) {
        Member member = memberTable.get(userId);

        try {
            if (member != null) {
                throw new DuplicateMemberIdException("중복된 ID입니다.");
            }
        } catch (DuplicateMemberIdException e) {
            e.getMessage();
        } finally {
            return member;
        }
    }



// TODO: 아이디 중복 검증 만들어보기
//
//    public Boolean duplicate(String userId) {
//        for(String id : memberTable.keySet()) {
//            if(userId == id) {
//                return true;
//            }
//        }
//        return false;
//    }


// TODO: @JsonNaming Test
// @JsonNaming Test
//      public Member save(Member member) {
//          memberTable.put(member.getUserId(), member);
//          Member savedMember = memberTable.get(member.getUserId());
//          return savedMember;
//      }
}

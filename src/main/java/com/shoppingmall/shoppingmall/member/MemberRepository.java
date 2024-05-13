package com.shoppingmall.shoppingmall.member;

import org.springframework.stereotype.Repository;

import java.util.*;

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
        return memberTable.get(userId);
    }

//    public Boolean duplicate(String userId) {
//        for(String id : memberTable.keySet()) {
//            if(userId == id) {
//                return true;
//            }
//        }
//        return false;
//    }

    // @JsonNaming Test
//      public Member save(Member member) {
//          memberTable.put(member.getUserId(), member);
//          Member savedMember = memberTable.get(member.getUserId());
//          return savedMember;
//      }
}

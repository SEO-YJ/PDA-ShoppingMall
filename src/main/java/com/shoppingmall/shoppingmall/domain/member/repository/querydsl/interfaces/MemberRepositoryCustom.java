package com.shoppingmall.shoppingmall.domain.member.repository.querydsl.interfaces;

import com.shoppingmall.shoppingmall.domain.member.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    public List<Member> getMembers();
}

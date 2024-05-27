package com.shoppingmall.shoppingmall.domain.member.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shoppingmall.shoppingmall.domain.member.entity.Member;
import com.shoppingmall.shoppingmall.domain.member.repository.querydsl.interfaces.MemberRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.shoppingmall.shoppingmall.domain.member.entity.QMember.member;

// Querydsl 기능을 사용하는 ProductRepositoryImpl 클래스
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> getMembers() {
        return queryFactory
                .selectFrom(member)
                .fetch();
    }
}

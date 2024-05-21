package com.shoppingmall.shoppingmall.member;

import exercise.exception.InputBoundErrorException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;

// TODO: 24.05.14까지 최신화
@Repository
public class MemberRepository {
    private Map<String, Member> memberTable = new HashMap<>();

    @Autowired
    private EntityManager entityManager;

    @Autowired
    DataSource dataSource;

    public Connection makeConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }



    @Transactional
    public String save(Member member) {
        // memberTable.put(member.getUserId(), member);
        entityManager.persist(member);

        // Member savedMember =  memberTable.get(member.getUserId());
        Member savedMember = entityManager.find(Member.class, member.getId());
        System.out.println("/users : repository - " + savedMember);
        return savedMember.getUserId();
    }

    public Member findById(String userId) {
        return memberTable.get(userId);
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

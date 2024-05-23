package com.shoppingmall.shoppingmall.member;

import com.shoppingmall.shoppingmall.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;

// TODO: 24.05.14까지 최신화
@Repository
public class MemberRepository {
    @Autowired
    private EntityManager entityManager;

    public void save(Member member) {
        // memberTable.put(member.getUserId(), member);
        entityManager.persist(member);
    }

    public Member findById(int id) {
        // Member savedMember =  memberTable.get(member.getUserId());
        return entityManager.find(Member.class, id);
    }

    public Member findByUserID(String userId) {
        String jpql = "SELECT m FROM Member m WHERE m.userId = :userId";
        // entityManager 플러시모드 => AUTO => 커밋이나 JPQL 쿼리 실행할 때 플러시 자동 발생
        System.out.println("플러시모드" + entityManager.getFlushMode());

        return entityManager.createQuery(jpql, Member.class)
                .setParameter("userId", userId)
                .getSingleResult();
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


// TODO: DataSource Test
//@Autowired
//DataSource dataSource;
//
//public Connection makeConnection() {
//    return DataSourceUtils.getConnection(dataSource);
//}
//package com.shoppingmall.shoppingmall.domain.member;
//
//import com.shoppingmall.shoppingmall.domain.member.entity.Member;
//import com.shoppingmall.shoppingmall.domain.member.repository.MemberRepository;
//import jakarta.persistence.EntityManager;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Optional;
//
//// TODO: 24.05.14까지 최신화
////@Repository
//public class MemberMapRepository implements MemberRepository {
//    @Autowired
//    private EntityManager entityManager;
//
//    public Member save(Member member) {
//        // memberTable.put(member.getUserId(), member);
//        entityManager.persist(member);
//        return member;
//    }
//
//    @Override
//    public Optional<Member> findByUserId(String id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Member> findById(Long id) {
//        return Optional.empty();
//    }
//
////    public Member findById(int id) {
////        // Member savedMember =  memberTable.get(member.getUserId());
////        return entityManager.find(Member.class, id);
////    }
//
//    public Optional<Member> findByUserID(String userId) {
//        String jpql = "SELECT m FROM Member m WHERE m.userId = :userId";
//        // entityManager 플러시모드 => AUTO => 커밋이나 JPQL 쿼리 실행할 때 플러시 자동 발생
//        System.out.println("플러시모드" + entityManager.getFlushMode());
//
//        Member foundMember =  entityManager.createQuery(jpql, Member.class)
//                .setParameter("userId", userId)
//                .getSingleResult();
//
//        if (foundMember == null) {
//            return null;
//        } else {
//            return Optional.ofNullable(foundMember);
//        }
//
//    }
//}
//
//// TODO: 아이디 중복 검증 만들어보기
////
////    public Boolean duplicate(String userId) {
////        for(String id : memberTable.keySet()) {
////            if(userId == id) {
////                return true;
////            }
////        }
////        return false;
////    }
//
//
//// TODO: @JsonNaming Test
//// @JsonNaming Test
////      public Member save(Member member) {
////          memberTable.put(member.getUserId(), member);
////          Member savedMember = memberTable.get(member.getUserId());
////          return savedMember;
////      }
//
//
//// TODO: DataSource Test
////@Autowired
////DataSource dataSource;
////
////public Connection makeConnection() {
////    return DataSourceUtils.getConnection(dataSource);
////}
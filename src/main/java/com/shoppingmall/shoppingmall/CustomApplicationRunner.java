//package com.shoppingmall.shoppingmall;
//
//import com.shoppingmall.shoppingmall.member.MemberJPARepository;
//import com.shoppingmall.shoppingmall.member.MemberRepository;
//import com.shoppingmall.shoppingmall.member.entity.Member;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class CustomApplicationRunner implements ApplicationRunner {
//
//    @Autowired
//    MemberJPARepository memberJPARepository;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("진짜 동시에 실행된다니까?");
//        Optional<Member> resultMember = memberJPARepository.findByUserId("yujunnii");
//
//        if(resultMember.isEmpty())
//            System.out.println("Empty!!!");
//        else
//            System.out.println(resultMember.get().getUserId());
//    }
//}
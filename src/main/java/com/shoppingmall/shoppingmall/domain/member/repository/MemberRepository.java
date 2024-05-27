package com.shoppingmall.shoppingmall.domain.member.repository;
import com.shoppingmall.shoppingmall.domain.member.entity.Member;
import com.shoppingmall.shoppingmall.domain.member.repository.querydsl.interfaces.MemberRepositoryCustom;
import com.shoppingmall.shoppingmall.domain.product.entity.Product;
import com.shoppingmall.shoppingmall.domain.product.repository.querydsl.interfaces.ProductRepositoryCustom;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Optional<Member> findByUserId(String userId);
}
package com.shoppingmall.shoppingmall.domain.order.repository;

import com.shoppingmall.shoppingmall.domain.order.entity.Order;
import com.shoppingmall.shoppingmall.domain.order.repository.querydsl.interfaces.OrderRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

}

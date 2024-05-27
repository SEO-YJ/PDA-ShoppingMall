package com.shoppingmall.shoppingmall.domain.order.repository.querydsl.interfaces;

import com.shoppingmall.shoppingmall.domain.member.entity.Member;
import com.shoppingmall.shoppingmall.domain.order.entity.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> getOrders();
}

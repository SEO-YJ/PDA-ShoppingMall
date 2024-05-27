package com.shoppingmall.shoppingmall.domain.order.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shoppingmall.shoppingmall.domain.member.entity.Member;
import com.shoppingmall.shoppingmall.domain.order.entity.Order;
import com.shoppingmall.shoppingmall.domain.order.repository.querydsl.interfaces.OrderRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.shoppingmall.shoppingmall.domain.order.entity.QOrder.order;
import static com.shoppingmall.shoppingmall.domain.product.entity.QProduct.product;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> getOrders() {
        return queryFactory
                .selectFrom(order)
                .fetch();
    }
}

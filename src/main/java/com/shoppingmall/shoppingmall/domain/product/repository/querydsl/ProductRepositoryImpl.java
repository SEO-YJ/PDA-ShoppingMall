package com.shoppingmall.shoppingmall.domain.product.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shoppingmall.shoppingmall.domain.product.entity.Product;
import com.shoppingmall.shoppingmall.domain.product.repository.querydsl.interfaces.ProductRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.shoppingmall.shoppingmall.domain.product.entity.QProduct.product;

// Querydsl 기능을 사용하는 ProductRepositoryImpl 클래스
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> getProducts() {
        return queryFactory
                .selectFrom(product)
                .fetch();
    }
}

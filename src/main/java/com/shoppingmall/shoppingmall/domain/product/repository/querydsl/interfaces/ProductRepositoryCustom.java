package com.shoppingmall.shoppingmall.domain.product.repository.querydsl.interfaces;

import com.shoppingmall.shoppingmall.domain.product.entity.Product;

public interface ProductRepositoryCustom {
    Product registerProduct(Product product);
}
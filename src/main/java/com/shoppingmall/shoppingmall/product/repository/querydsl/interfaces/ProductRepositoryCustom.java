package com.shoppingmall.shoppingmall.product.repository.querydsl.interfaces;

import com.shoppingmall.shoppingmall.product.entity.Product;

public interface ProductRepositoryCustom {
    Product registerProduct(Product product);
}

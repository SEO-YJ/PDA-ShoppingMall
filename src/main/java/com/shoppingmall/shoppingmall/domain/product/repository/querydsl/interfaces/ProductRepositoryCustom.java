package com.shoppingmall.shoppingmall.domain.product.repository.querydsl.interfaces;


import com.shoppingmall.shoppingmall.domain.product.entity.Product;

import java.util.List;


public interface ProductRepositoryCustom {
    List<Product> getProducts();
}
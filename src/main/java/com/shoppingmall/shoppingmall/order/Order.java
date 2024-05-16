package com.shoppingmall.shoppingmall.order;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.shoppingmall.shoppingmall.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// 주문 Entity(Domain Object)
@Getter
@Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {
    private int id;
    private Product product;
    private int count;

    // @RequiredArgsConstructor로 대체
    Order(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product=" + product +
                ", count=" + count +
                '}';
    }
}

// TODO: 강사님 코드(24.05.16)
//@Getter
//@Setter
//@RequiredArgsConstructor
//public class Order {
//    int id;
//    Product product;  // Domain = Object
//    int count;
//
//    // DB column : Orders orders id or id
//    // Orders 테이블에 ordersId 이런식으로 해도 되나
//    // 의미가 '중복'되는 느낌이 있기에 id로 작명하는 것이 괜찮을 것 같다.
//}
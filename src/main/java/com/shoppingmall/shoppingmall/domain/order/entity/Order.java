package com.shoppingmall.shoppingmall.domain.order.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.shoppingmall.shoppingmall.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.type.SqlTypes;

// 주문 Entity(Domain Object)
@Getter
@Setter
@NoArgsConstructor
@Entity
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
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
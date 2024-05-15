package com.shoppingmall.shoppingmall.product;

import lombok.Getter;
import lombok.Setter;

// TODO: 24.05.14까지 최신화
@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private int price;
    private String description;
    private int categoryId;

    // getter, setter
    // setter는 가능한 도메인 객체에는 만들면 안된다.
    // TODO setter는 DTO 등장하고. 지우러 오자!
}

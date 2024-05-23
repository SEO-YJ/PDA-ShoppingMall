package com.shoppingmall.shoppingmall.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 기능: Product Entity 클래스
 * 용도: Product에 대한 Entity로 사용합니다.
 *
 * @author 최종 수정자: 서유준
 * @version 1.0, 작업 내용: 24.05.23 최신화
 * @see Product#toString()
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product {
    /**
     * PK
     * Product를 DB에서 구분하기 위한 정보
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Product를 구분하기 위한 상품명 정보
     */
    @Column(unique=true)
    private String name;
    /**
     * Product의 가격 정보
     */
    private int price;
    /**
     * Product의 상품 설명 정보
     */
    private String description;
    /**
     * Product의 상품 설명 요약 정보
     */
    private String summary;
    /**
     * Product를 카테고리 별로 구분하기 위한 카테고리 id 정보
     */
    private int categoryId;

    public Product(String name, int price, String description, String summary, int categoryId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.summary = summary;
        this.categoryId = categoryId;
    }

    /**
     * Product Entity의 멤버 필드를 반환하는 메소드입니다.
     *
     * @see Product
     * @return Product 객체의 멤버들을 문자열로 묶어 반환합니다.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", summary='" + summary + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
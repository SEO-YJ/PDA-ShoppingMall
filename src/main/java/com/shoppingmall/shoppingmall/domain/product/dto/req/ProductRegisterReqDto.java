package com.shoppingmall.shoppingmall.domain.product.dto.req;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.shoppingmall.shoppingmall.domain.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 기능: Product 개별 등록을 위한 RequestDto 클래스
 * 용도: Product를 개별 등록하기 위한 Dto로 사용합니다..
 *
 * @author 최종 수정자: 서유준
 * @version 1.0, 작업 내용: 24.05.23 최신화
 * @see ProductRegisterReqDto#convertToEntity()
 * @see ProductRegisterReqDto#toString()
 */
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductRegisterReqDto {
    /**
     * DB에서 Product를 구분하기 위한 id 정보
     */
    private int id;
    /**
     * Product를 구분하기 위한 상품명 정보
     */
    @NotBlank(message = "상품명은 필수 입력입니다.")
    private String name;
    /**
     * Product의 가격 정보
     */
    private int price;
    /**
     * Product의 상품 설명 정보
     */
    @NotBlank(message = "상품 설명은 필수 입력입니다.")
    @Size(min = 1, max = 100, message = "상품 설명은 1 ~ 100자 이여야 합니다!")
    private String description;
    /**
     * Product의 상품 설명 요약 정보
     */
    @NotBlank(message = "상품 설명 요약은 필수 입력입니다.")
    @Size(min = 1, max = 20, message = "상품 설명 요약은 1 ~ 100자 이여야 합니다!")
    private String summary;
    /**
     * Product를 카테고리 별로 구분하기 위한 카테고리 id 정보
     */
    private int categoryId;

    public Product convertToEntity() {
        return new Product(name, price, description, summary,categoryId);
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", summary='" + summary + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}

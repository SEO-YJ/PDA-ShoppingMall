package com.shoppingmall.shoppingmall.domain.order.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderDto {
    private int productId;
    private int count;

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "productId=" + productId +
                ", count=" + count +
                '}';
    }
}

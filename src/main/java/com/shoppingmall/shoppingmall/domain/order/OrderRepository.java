package com.shoppingmall.shoppingmall.domain.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class OrderRepository {
    Map<Integer, Order> orderTable
            = new HashMap<>();

    private int id = 0; // DB auto_increment
    public Integer save(Order order) {
        log.info("productName  = {}", order.getProduct().getName());
//        log.info("quantity = {}", order.getQuantity());

        // 여기서 Order 필드의 id 값 추가
        // auto_increment도 진행
        order.setId(id++);

        orderTable.put(order.getId(), order);
        System.out.println(
                "/orders : repository - " + orderTable.get(id-1));
        return orderTable.get(id-1).getId();
    }
}

// TODO: 강사님 코드 - saveOrder(24.05.16)
//public void saveOrder(Order order) {
//    log.info("productName = {}", order.productName);
//    log.info("count = {}", order.getCount());
//    order.setId(id++);
//    orderTable.put(id++, order);
//}
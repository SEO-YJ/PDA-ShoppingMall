package com.shoppingmall.shoppingmall.domain.order.service;

import com.shoppingmall.shoppingmall.domain.order.dto.req.OrderDto;
import com.shoppingmall.shoppingmall.domain.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    OrderRepository orderRepository;
    public Integer order(OrderDto orderDTO) {
        // TODO: Service로 이사 갈거예요. DTO -> Entity (생성자 방법)
        // 1. Order 객체 생성
//        Order requestOrder = new Order(orderedProduct, orderDTO.getCount());


//        return orderRepository.save(order);
        return 1;
    }
}

// TODO: 강사님 코드 - 주문 등록 API(24.05.16)
//public void orderProduct(Order order) {
//    orderRepository.saveOrder(order);
//}
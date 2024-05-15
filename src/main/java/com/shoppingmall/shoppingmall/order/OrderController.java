package com.shoppingmall.shoppingmall.order;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: 24.05.14까지 최신화
@RestController
public class OrderController {
    // test /test
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}

// TODO: OrderController 만들어보기
//@RestController
//@RequestMapping("/orders")
//public class OrderController {
//
//    @GetMapping
//    public String getOrders() {
//        System.out.println("OrderController - getOrders() called");
//        return "Orders retrieved successfully";
//    }
//}
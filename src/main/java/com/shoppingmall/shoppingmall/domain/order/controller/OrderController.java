package com.shoppingmall.shoppingmall.domain.order.controller;

import com.shoppingmall.shoppingmall.domain.order.service.OrderService;
import com.shoppingmall.shoppingmall.domain.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

// TODO: 24.05.14까지 최신화
@Slf4j
@RestController
@AllArgsConstructor
public class OrderController {
    OrderService orderService;
    ProductService productService;

//    @PostMapping("/orders")
//    public ApiUtils.ApiResult order(@RequestBody OrderDto orderDto) {
//        // 1. orderDTO의 productID로 Product 조회
//        if(Validator.isNumber(orderDto.getProductId())) {
//            Product orderedProduct = productService.findProduct(orderDto.getProductId());
//
//            if(orderedProduct == null) {
//                return error("productID에 해당 하는 Product가 존재하지 않음", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            if (Validator.isNumber(orderDto.getCount())) {
//                // 주문 log 출력
//                log.info(orderDto.toString());
//
//
//                // Repository에 주문 저장 시도
////                Integer orderId = orderService.order(requestOrder);
////            {
////	                “success” : True,
////	                “response” : 응답 데이터(객체),
////	                “error” : null
////            }
//                try {
//                    System.out.println("hi");
////                    log.info(String.valueOf(orderId));
//                } catch (NullPointerException e) {
//                    return error("DB에 저장되지 않음", HttpStatus.INTERNAL_SERVER_ERROR);
//                }
////                return success(orderId);
//            } else {
//                // 수량에 문자가 포함될 경우
//                return error("수량에 문자열 포함", HttpStatus.BAD_REQUEST);
//            }
//        }
//        else {
//            // productID가 숫자로 이루어져 있지 않을 경우
//            return error("product ID가 숫자로 이루어져 있지 않음", HttpStatus.BAD_REQUEST);
//        }
//        return null;
//    }

//    // TODO: 강사님 코드 1 - DTO로 변환하기 위한 코드 (24.05.16)
//    @PostMapping("/orders");
//    public void orderProduct(@RequestBody OrderDTO orderDTO) {
//        Product orderedProduct = productService.findProduct(orderDTO.getProductId());
//        Order requestOrder = new Order(orderedProduct, orderDTO.getCount());
//        orderService.orderProduct(order);

//    }
}

// TODO: Filter, Interceptor, AOP 용 OrderController 만들어보기
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
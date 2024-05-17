package com.shoppingmall.shoppingmall.product;

import com.shoppingmall.shoppingmall.utils.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class ProductController {

//    @Autowired // DI
    ProductService productService;
    
    // 상품 개별 등록
    @PostMapping("/products")
    public ResponseEntity registerProduct(@RequestBody Product product) {
        // * 유효성 검사 : name(영어), price(숫자)
        // Validator 통해서 유효성 검사
        if(Validator.isAlpha(product.getName()) && Validator.isNumber(product.getPrice()) && Validator.isNumber(product.getCategoryId())) {
            // 상품 등록의 이름을 log로 출력
            log.info(product.getName());
            // Repository에 저장 시도
            Product savedProduct = productService.registerProduct(product);

            // Repository에 저장 실패
            // 예외 발생
//            if(savedProduct == null)
//                return "등록 실패";
            // TODO NPE(Null Pointer Exception) 처리 (try - catch)
            try{
                log.info(savedProduct.getName());

            } catch(NullPointerException e) {
                // Null 일 경우
                // TODO 1 status code
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            // Repository에 저장 성공
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            // TODO 2 status code
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 상품 전체, 카테고리별 조회
    @GetMapping("/products")
    public ResponseEntity<List<Product>> findProducts(@RequestParam("limit") int limit,
                                                      @RequestParam("currentPage") int currentPage,
                                                      @RequestParam(value = "categoryId", required = false) Integer categoryId) {
        log.info("limit = {}", limit);
        log.info("currentPage = {}", currentPage);
        log.info("categoryId = {}", categoryId);

        // categoryId가 Wrapper Class로 감싸져 있으므로(Integer 이므로)
        // null 체크 가능

        // TODO null 체크는 어디서 해야할까?
        if(categoryId == null) {
            List<Product> products = productService.findProducts(limit, currentPage);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            List<Product> products = productService.findProducts(limit, currentPage, categoryId);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }

    }
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> findProducts(@RequestParam(required = false) Integer limit,
//                                                      @RequestParam(required = false) Integer currentPage,
//                                                      @RequestParam(required = false) Long categoryId) {
//        try {
//            Map<String, Object> response = productService.getProducts(limit, currentPage, categoryId);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            // 로깅 처리 등 오류 관련 처리
//            return ResponseEntity.status(404).body("Error: " + e.getMessage());
//        }
//    }



    // 상품 찾는 방법
    // 이름, id, 필드

    // intellij 메서드 단축키
    // alt + shift + enter

    // 1인 과제
    // 1. Product 반환 필드 : id가 없다.
    // 2. id 숫자만 들어온 거 맞는지 유효성 검사 추가

    // 상품 개별 조회
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable int id) {
        if(!Validator.isNumber(id)){
            // Logger log = LoggerFactory.getLogger(ProductController.class);
            log.info(id + " haha");
            log.info("id {}", "haha");

            // 요청할 때, 값이 잘 못 전달 된 경우
            // 즉, 요청이 잘 못된 경우에는 BAD_REQUEST
            // TODO log INFO 레벨 id
            // type : generics
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product resultProduct = productService.findProduct(id);

        if(resultProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resultProduct, HttpStatus.OK);
    }

    // 상품 한 개 삭제
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
        if(!Validator.isNumber(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // TODO 삭제에 성공, 실패
        // 내 코드
        Product product = productService.deleteProduct(id);
        if(product != null)
            return new ResponseEntity<>((HttpStatus.OK));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        // 강사님 코드
//        productService.deleteProduct();
//        Product product = productService.findProduct(id);
//        if(product == null)
//            return new ResponseEntity<>(HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/products/delete")
    public ResponseEntity deleteProducts(@RequestBody Map<String, List<Integer>> deleteRequest) {
        List<Integer> productIds = deleteRequest.get("productIds");

        if (productIds.isEmpty()) {
            log.info("productIds가 없어");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        productService.deleteProducts(productIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

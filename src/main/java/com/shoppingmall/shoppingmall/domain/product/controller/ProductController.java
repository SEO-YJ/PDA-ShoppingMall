package com.shoppingmall.shoppingmall.domain.product.controller;

import com.shoppingmall.shoppingmall.domain.product.service.ProductService;
import com.shoppingmall.shoppingmall.domain.product.dto.req.ProductRegisterReq;
import com.shoppingmall.shoppingmall.domain.product.entity.Product;
import com.shoppingmall.shoppingmall.exception.DuplicateMemberIdException;
import com.shoppingmall.shoppingmall.exception.DuplicateProductNameException;
import com.shoppingmall.shoppingmall.utils.ApiUtils;
import com.shoppingmall.shoppingmall.utils.Validator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.shoppingmall.shoppingmall.utils.ApiUtils.error;
import static com.shoppingmall.shoppingmall.utils.ApiUtils.success;

import java.util.Map;
import java.util.Optional;

/**
 * 기능: Product 도메인의 Controller 클래스
 * 용도: Product 도메인에 대한 응답을 처리하는 Controller로 사용합니다.
 *
 * @author 최종 수정자: 서유준
 * @version 1.0, 작업 내용: 24.05.25 최신화
 * @see ProductController#registerProduct(ProductRegisterReq)
 * @see ProductController#findProduct(int) 
 * @see ProductController#findProducts(int, int, Integer) 
 * @see ProductController#deleteProduct(int) 
 * @see ProductController#deleteProducts(Map)
 */
@Slf4j
@RestController
@AllArgsConstructor
public class ProductController {

    /**
     * Product에 대한 요청을 처리하기 위해 사용되는 Service 계층 변수
     */
    ProductService productService;

    /**
     *  상품 개별 등록을 하는 메소드입니다.
     *
     * @param productRegisterReq 클라이언트에게 전달 받은 데이터를 저장할 요청 product dto 객체입니다.
     * @see ProductController
     * @return 200(OK) : 정상적으로 MemberDto가 DB에 저장된 경우
     * @return 201(Created) : 정상적으로 MemberDto가 DB에 저장된 경우
     * @return 400(Bad Request): 유효성 검사에 실패할 경우
     * @return 409(Conflict) : 상품이름이 DB에 존재하는 경우
     * @return 500(Internal server error) : 전달한 ProductRegistReq가 DB에 저장되지 않은 경우
     */
    @PostMapping("/products/register")
    public ApiUtils.ApiResult registerProduct(@Valid @RequestBody ProductRegisterReq productRegisterReq) {
            log.info(productRegisterReq.getName());

            if(isDuplicateName(productRegisterReq)) {
                return error("상품 이름 중복", HttpStatus.CONFLICT);
            }

            Product savedProduct = productService.registerProduct(productRegisterReq);

            try{
                log.info(savedProduct.getName());
                return success(savedProduct.toString());
            } catch(NullPointerException e) {
                return error("상품이 저장되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    /**
     *  상품명 이름의 중복을 체크하는 메소드입니다.
     *
     * @param productRegisterReq 클라이언트에게 전달 받은 데이터를 저장할 요청 productRegister dto 객체입니다.
     * @see ProductController
     * @return 200(OK) : 정상적으로 중복 체크를 진행한 경우
     * @return 409(Conflict) : 상품 이름이 DB에 저장되어 있는 경우
     */
    @PostMapping("/products/check")
    public ApiUtils.ApiResult<String> checkUsableId(@RequestBody ProductRegisterReq productRegisterReq) {
        if(isDuplicateName(productRegisterReq)) {
            throw new DuplicateProductNameException("이미 사용 중인 상품명입니다.");
        } else {
            return success("사용 가능한 상품명입니다.");
        }
    }

    /**
     *  상품을 개별 조회하는 메소드입니다.
     *
     * @param name 클라이언트에게 전달 받은 상품명 변수입니다. 상품명은 공개되도 되는 정보이므로 url에 pathvariable 형태로 전달 받았습니다.
     * @see ProductController
     * @return 200(OK) : 정상적으로 상품을 조회한 경우
     * @return 400(BAD_REQUEST): 상품명이 비어있거나 null 인 경우
     * @return 404(NOT_FOUND) : 상품명과 동일한 상품이 DB에 없는 경우
     */
    @GetMapping("/products/{name}")
    public ApiUtils.ApiResult<Product> findProduct(@PathVariable("name") String name) {
        if(name.isBlank()){
            // 요청할 때, 값이 잘 못 전달 된 경우
            // 즉, 요청이 잘 못된 경우에는 BAD_REQUEST
            return error(null, HttpStatus.BAD_REQUEST);
        }
        log.info(name);
        Optional<Product> resultProduct = productService.findProduct(name);
        if(resultProduct.isPresent()){
            return success(resultProduct.get());
        } else {
            return error(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  상품을 1개 조회하는 메소드입니다.
     *
     * @param name 클라이언트에게 전달 받은 상품명 변수입니다. 상품명은 공개되도 되는 정보이므로 url에 pathvariable 형태로 전달 받았습니다.
     * @see ProductController
     * @return 200(OK) : 정상적으로 상품을 조회한 경우
     * @return 400(BAD_REQUEST): 상품명이 비어있거나 null 인 경우
     * @return 404(NOT_FOUND) : 상품명과 동일한 상품이 DB에 없는 경우
     */
    @DeleteMapping("/products/{name}")
    public ApiUtils.ApiResult<String> deleteProduct(@PathVariable("name") String name) {
        if(name.isBlank()) {
            // 요청할 때, 값이 잘 못 전달 된 경우
            // 즉, 요청이 잘 못된 경우에는 BAD_REQUEST
            return error(null, HttpStatus.BAD_REQUEST);
        }

        Optional<Product> findProduct = productService.findProduct(name);

        if(findProduct.isPresent()){
            productService.deleteProduct(name);
            Optional<Product> deleteProduct = productService.findProduct(name);
            if(deleteProduct.isPresent()){
                return error(String.format("(상품명: %s)가 삭제되지 않았습니다.", name), HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return success(String.format("(상품명: %s)가 삭제되었습니다.", name));
            }
        } else {
            return error(String.format("(상품명: %s)가 DB에 존재하지 않습니다.", name), HttpStatus.NOT_FOUND);
        }
    }


    private boolean isDuplicateName(ProductRegisterReq productRegisterReq) {
        return productService.checkDuplicated(productRegisterReq);
    }

//    // 상품 전체, 카테고리별 조회
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> findProducts(@RequestParam("limit") int limit,
//                                                      @RequestParam("currentPage") int currentPage,
//                                                      @RequestParam(value = "categoryId", required = false) Integer categoryId) {
//        log.info("limit = {}", limit);
//        log.info("currentPage = {}", currentPage);
//        log.info("categoryId = {}", categoryId);
//
//        // categoryId가 Wrapper Class로 감싸져 있으므로(Integer 이므로)
//        // null 체크 가능
//
//        // TODO null 체크는 어디서 해야할까?
//        if(categoryId == null) {
//            List<Product> products = productService.findProducts(limit, currentPage);
//            return new ResponseEntity<>(products, HttpStatus.OK);
//        } else {
//            List<Product> products = productService.findProducts(limit, currentPage, categoryId);
//            return new ResponseEntity<>(products, HttpStatus.OK);
//        }
//
//    }
//
//    // 상품 찾는 방법
//    // 이름, id, 필드
//
//    // intellij 메서드 단축키
//    // alt + shift + enter
//
//    // 1인 과제
//    // 1. Product 반환 필드 : id가 없다.
//    // 2. id 숫자만 들어온 거 맞는지 유효성 검사 추가
//


//
//    @PostMapping("/products/delete")
//    public ResponseEntity deleteProducts(@RequestBody Map<String, List<Integer>> deleteRequest) {
//        List<Integer> productIds = deleteRequest.get("productIds");
//
//        if (productIds.isEmpty()) {
//            log.info("productIds가 없어");
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        productService.deleteProducts(productIds);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
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

// 강사님 코드
//        productService.deleteProduct();
//        Product product = productService.findProduct(id);
//        if(product == null)
//            return new ResponseEntity<>(HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
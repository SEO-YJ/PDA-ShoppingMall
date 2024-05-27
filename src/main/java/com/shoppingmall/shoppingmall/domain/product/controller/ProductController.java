package com.shoppingmall.shoppingmall.domain.product.controller;

import com.shoppingmall.shoppingmall.domain.product.service.ProductService;
import com.shoppingmall.shoppingmall.domain.product.dto.req.ProductRegisterReq;
import com.shoppingmall.shoppingmall.domain.product.entity.Product;
import com.shoppingmall.shoppingmall.exception.DuplicateMemberIdException;
import com.shoppingmall.shoppingmall.exception.DuplicateProductNameException;
import com.shoppingmall.shoppingmall.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import static com.shoppingmall.shoppingmall.utils.ApiUtils.error;
import static com.shoppingmall.shoppingmall.utils.ApiUtils.success;

import java.util.Map;

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
    // 상품 개별 조회
//    @GetMapping("/products/{id}")
//    public ApiUtils.ApiResult<Product> findProduct(@PathVariable int id) {
//        if(!Validator.isNumber(id)){
//            // Logger log = LoggerFactory.getLogger(ProductController.class);
//
//            // 요청할 때, 값이 잘 못 전달 된 경우
//            // 즉, 요청이 잘 못된 경우에는 BAD_REQUEST
//            // TODO log INFO 레벨 id
//            // type : generics
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        Product resultProduct = productService.findProduct(id);
//
//        if(resultProduct == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(resultProduct, HttpStatus.OK);
//    }
//
//    // 상품 한 개 삭제
//    @DeleteMapping("/products/{id}")
//    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
//        if(!Validator.isNumber(id)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        // TODO 삭제에 성공, 실패
//        Product product = productService.deleteProduct(id);
//        if(product != null)
//            return new ResponseEntity<>((HttpStatus.OK));
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
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
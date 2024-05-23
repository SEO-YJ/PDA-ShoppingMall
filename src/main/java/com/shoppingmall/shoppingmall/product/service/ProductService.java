package com.shoppingmall.shoppingmall.product.service;

import com.shoppingmall.shoppingmall.product.repository.ProductRepository;
import com.shoppingmall.shoppingmall.product.dto.ProductRegisterReqDto;
import com.shoppingmall.shoppingmall.product.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor // 필드로 생성자 코드 구현
public class ProductService {
    ProductRepository productRepository;

    public Product registerProduct(ProductRegisterReqDto productRegisterReqDto) {
        // Dto -> Entity 변환
        Product requestProduct = productRegisterReqDto.convertToEntity();
        Product responseProduct;

        try {
            responseProduct = productRepository.save(requestProduct);
        } catch (DataIntegrityViolationException e) {
            // 이곳에서 예외를 처리합니다.
            System.err.println("Duplicate entry detected: " + e.getMessage());
        }
        // 중복 검사를 DB에 맡길? or 코드 단에서 한번 더?

        return responseProduct;
    }

//    public List<Product> findProducts(int limit, int currentPage) {
//        return productRepository.findProducts(limit, currentPage);
//    }
//    public List<Product> findProducts(int limit, int currentPage, int categoryId) {
//        return productRepository.findProducts(limit, currentPage, categoryId);
//    }
//    public Product findProduct(int id) {
//        return productRepository.findProduct(id);
//    }
//
//    public Product deleteProduct(int id) {
//        return productRepository.deleteProduct(id);
//    }
//
//    public void deleteProducts(List<Integer> productIds) {
//        productRepository.deleteProducts(productIds);
//    }
}

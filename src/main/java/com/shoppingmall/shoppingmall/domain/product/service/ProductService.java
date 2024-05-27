package com.shoppingmall.shoppingmall.domain.product.service;

import com.shoppingmall.shoppingmall.domain.product.dto.req.ProductRegisterReqDto;
import com.shoppingmall.shoppingmall.domain.product.repository.ProductRepository;
import com.shoppingmall.shoppingmall.domain.product.entity.Product;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor // 필드로 생성자 코드 구현
public class ProductService {
    ProductRepository productRepository;

    @Transactional
    public Optional<Product> registerProduct(ProductRegisterReqDto productRegisterReqDto) {
        Product requestProduct = productRegisterReqDto.convertToEntity();
        Product responseProduct;

        try {
            responseProduct = productRepository.save(requestProduct);
            return Optional.of(responseProduct); // 성공적으로 저장된 경우 저장된 객체를 반환
        } catch (DataIntegrityViolationException e) {
            // 중복 에러 발생 시 null을 반환
            System.err.println("Duplicate entry detected: " + e.getMessage());
            return Optional.empty();
        }
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

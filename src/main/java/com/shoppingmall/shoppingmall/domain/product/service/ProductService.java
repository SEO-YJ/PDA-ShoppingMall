package com.shoppingmall.shoppingmall.domain.product.service;

import com.shoppingmall.shoppingmall.domain.member.entity.Member;
import com.shoppingmall.shoppingmall.domain.product.dto.req.ProductRegisterReq;
import com.shoppingmall.shoppingmall.domain.product.repository.ProductRepository;
import com.shoppingmall.shoppingmall.domain.product.entity.Product;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor // 필드로 생성자 코드 구현
@Slf4j
public class ProductService {
    ProductRepository productRepository;

    @Transactional
    public Product registerProduct(ProductRegisterReq productRegisterReq) {
        Product requestProduct = productRegisterReq.convertToEntity();
        return productRepository.save(requestProduct);
    }

    public boolean checkDuplicated(ProductRegisterReq productRegisterReq) {
        Product requestProduct = productRegisterReq.convertToEntity();

        Optional<Product> existProduct = productRepository.findByName(requestProduct.getName());

        if(existProduct.isPresent()) {
            log.info(existProduct.get().getName());
            return true;
        } else {
            return false;
        }
    }

//    public List<Product> findProducts(int limit, int currentPage) {
//        return productRepository.findProducts(limit, currentPage);
//    }
//    public List<Product> findProducts(int limit, int currentPage, int categoryId) {
//        return productRepository.findProducts(limit, currentPage, categoryId);
//    }

    public Optional<Product> findProduct(String name) {
        return productRepository.findByName(name);
    }

    @Transactional
    public void deleteProduct(String name) {
        productRepository.deleteByName(name);
    }
//
//    public void deleteProducts(List<Integer> productIds) {
//        productRepository.deleteProducts(productIds);
//    }
}

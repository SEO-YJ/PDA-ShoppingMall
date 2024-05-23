package com.shoppingmall.shoppingmall.product;

import com.shoppingmall.shoppingmall.product.dto.ProductRegisterReqDto;
import com.shoppingmall.shoppingmall.product.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor // 필드로 생성자 코드 구현
public class ProductService {
    ProductRepository productRepository;

    public Product registerProduct(ProductRegisterReqDto productRegisterReqDto) {
        // Dto -> Entity 변환
        Product requestProduct = productRegisterReqDto.convertToEntity();
        return productRepository.save(requestProduct);
    }

    public List<Product> findProducts(int limit, int currentPage) {
        return productRepository.findProducts(limit, currentPage);
    }
    public List<Product> findProducts(int limit, int currentPage, int categoryId) {
        return productRepository.findProducts(limit, currentPage, categoryId);
    }
    public Product findProduct(int id) {
        return productRepository.findProduct(id);
    }

    public Product deleteProduct(int id) {
        return productRepository.deleteProduct(id);
    }

    public void deleteProducts(List<Integer> productIds) {
        productRepository.deleteProducts(productIds);
    }
}

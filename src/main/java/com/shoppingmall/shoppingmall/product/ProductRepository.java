package com.shoppingmall.shoppingmall.product;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    Map<Integer, Product> productTable
            = new HashMap<>();
    int id = 0; // DB auto_increment

    public Product save(Product product) {
        // 여기서 Product 필드의 id 값 추가
        // auto_increment도 진행
        product.setId(id++);

        productTable.put(product.getId(), product);
        System.out.println(
                "/products : repository - " + productTable.get(id-1));
        return productTable.get(id-1);
    }

    // 기존 ProductRepository 클래스 내에 추가
    // product_table의 모든 값을 List로 반환
    // Map -> Stream -> List
    // limit, currentPage => 상품 id 범위

    // limit 4 / currentPage 1 => 0~3
    // currentPage -  1 = 0 * limit => 0
    // limit 4 / currentPage 2 => 4~7
    // currentPage - 1 = 1 * limit => 4
    // limit 4 / currentPage 3 => 8~11
    // currentPage - 1 = 2 * limit => 8
    // DB 연결하고 해결해야지
    public List<Product> findProducts(int limit, int currentPage) {
        return productTable.values().stream().toList();
    }

    public List<Product> findProducts(int limit, int currentPage, int categoryId) {
        List<Product> resultProducts = new ArrayList<>();

        for (Product product : productTable.values()) {
            if (product.getCategoryId() == categoryId) {
                resultProducts.add(product);
            }
        }
        return resultProducts;
    }

    public Product findProduct(int id) {
        // product_table map에서 id로 Product 조회
        return productTable.get(id);
    }

    public Product deleteProduct(int id) {
        return productTable.remove(id);
    }

    public void deleteProducts(List<Integer> productIds) {
        for (Integer id : productIds) {
            productTable.remove(id);
        }

//        for (int idx = 0; idx < productIds.size(); idx++) {
//            product_table.remove(productIds.get(idx));
//        }
    }
}

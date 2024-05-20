package com.shoppingmall.shoppingmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

// TODO: 24.05.14까지 최신화
@ServletComponentScan // Filter 등록을 위한 어노테이션
@SpringBootApplication
public class ShoppingmallApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShoppingmallApplication.class, args);
	}
}

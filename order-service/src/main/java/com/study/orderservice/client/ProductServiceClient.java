package com.study.orderservice.client;

import com.study.orderservice.dto.response.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")
public interface ProductServiceClient {
    @GetMapping("/api/products/{productId}")
    ProductResponseDto getProductById(@PathVariable("productId") Long productId);

    @PutMapping("/api/products/{productId}/stock")
    void updateProductStock(@PathVariable("productId") Long productId, @RequestParam("stock") Long stock);
}

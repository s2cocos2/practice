package com.study.productservice.controller;

import com.study.productservice.dto.ProductResponseDto;
import com.study.productservice.response.CommonResponse;
import com.study.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    //상품 전체 조회
    @GetMapping("")
    public ResponseEntity<CommonResponse<Page<ProductResponseDto>>> getAllProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc){
        return new ResponseEntity<>(productService.getAllProducts(page-1, size, sortBy, isAsc), HttpStatus.OK);
    }

    //상품 상세 조회
    @GetMapping("/{productId}")
    public ResponseEntity<CommonResponse<ProductResponseDto>> getProducts(@PathVariable Long productId){
        return new ResponseEntity<>(productService.getProducts(productId), HttpStatus.OK);
    }
}

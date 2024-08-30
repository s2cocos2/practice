package com.study.productservice.controller;

import com.study.productservice.dto.ProductRequestDto;
import com.study.productservice.dto.ProductResponseDto;
import com.study.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

//    @Value("${server.port}") // 애플리케이션이 실행 중인 포트를 주입받습니다.
//    private String serverPort;

    // 상품 등록
    @PostMapping("")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }

//    // 상품 등록
//    @PostMapping("")
//    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return productService.createProduct(requestDto, userDetails.getUser());
//    }

    //상품 전체 조회
    @GetMapping("")
    public Page<ProductResponseDto> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc){
        return productService.getProducts(page-1, size, sortBy, isAsc);
    }

    //상품 상세 조회
    @GetMapping("/{productId}")
    public ProductResponseDto getProducts(@PathVariable Long productId){
        return productService.getProduct(productId);
    }
    }

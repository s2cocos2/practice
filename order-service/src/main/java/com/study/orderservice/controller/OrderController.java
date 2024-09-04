package com.study.orderservice.controller;

import com.study.orderservice.dto.request.OrderRequestDto;
import com.study.orderservice.dto.response.OrderResponseDto;
import com.study.orderservice.response.CommonResponse;
import com.study.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    // 주문하기
    @PostMapping("")
    public ResponseEntity<CommonResponse> createOrder(@RequestBody OrderRequestDto requestDto){
        return new ResponseEntity<>(orderService.createOrder(requestDto), HttpStatus.CREATED);
    }

    // 주문 조회하기
    @GetMapping("{orderId}")
    public ResponseEntity<CommonResponse<OrderResponseDto>> getOrder(@PathVariable Long orderId){
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    // 주문 취소하기
    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<CommonResponse> cancelOrder(@PathVariable Long orderId){
        return new ResponseEntity<>(orderService.cancelOrder(orderId), HttpStatus.CREATED);
    }

    // 반품하기
    @PostMapping("/{orderId}/return/{productId}")
    public ResponseEntity<CommonResponse> returnProduct(@PathVariable Long orderId, @PathVariable Long productId){
        return new ResponseEntity<>(orderService.returnOrder(orderId, productId), HttpStatus.CREATED);
    }

}

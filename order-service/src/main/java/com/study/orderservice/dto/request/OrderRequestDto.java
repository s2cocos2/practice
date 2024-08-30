package com.study.orderservice.dto.request;

public record OrderRequestDto(

        Long productId,
        Long userId,
        Long count
) {
}

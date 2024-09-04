package com.study.orderservice.dto.request;

public record WishListRequestDto(

        Long memberId,
        Long productId,
        Long count
) {
}

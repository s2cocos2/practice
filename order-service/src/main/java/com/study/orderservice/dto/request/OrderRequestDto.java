package com.study.orderservice.dto.request;

import lombok.Getter;

@Getter
public record OrderRequestDto(

        Long productId,
        Long memberId,
        Long count
) {
}

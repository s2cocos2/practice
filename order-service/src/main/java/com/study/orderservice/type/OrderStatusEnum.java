package com.study.orderservice.type;

public enum OrderStatusEnum {
    // 주문 완료, 주문 취소, 배송 중, 배송 완료, 반품 완료
    ORDERED,
    SHIPPING,
    DELIVERED,
    CANCELED,
    RETURNED
}
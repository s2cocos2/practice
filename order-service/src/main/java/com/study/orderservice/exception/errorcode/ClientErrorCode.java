package com.study.orderservice.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClientErrorCode {
    // Orders
    NO_ORDER(HttpStatus.NOT_FOUND, "존재하지 않는 주문입니다."),
    NO_ORDER_DETAILS(HttpStatus.NOT_FOUND, "존재하지 않는 상세주문입니다."),
    INVALID_ORDER_STATUS(HttpStatus.FORBIDDEN, "주문을 취소할 수 있는 상태가 아닙니다."),
    RETURN_EXPIRED(HttpStatus.FORBIDDEN, "반품 가능 기간이 지났습니다."),

    // WishList
    NO_WISHLIST(HttpStatus.NOT_FOUND, "존재하지 않는 위시리스트 입니다.");

    private final HttpStatus statusCode;
    private final String msg;

}

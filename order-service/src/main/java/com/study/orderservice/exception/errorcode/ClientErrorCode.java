package com.study.orderservice.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClientErrorCode {
    // Member
    INVALID_ADMIN_TOKEN(HttpStatus.BAD_REQUEST, "관리자 암호가 일치하지 않습니다."),
    NO_ACCOUNT(HttpStatus.NOT_FOUND, "가입되지 않은 계정입니다."),
    INVALID_PASSWORDS(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호 입니다."),
    INVALID_REFRESHTOKEN(HttpStatus.BAD_REQUEST, "유효하지 않은 refresh token 입니다."),
    NO_REFRESHTOKEN(HttpStatus.BAD_REQUEST, "refresh token이 존재하지 않습니다."),

    // Orders
    NO_ORDER(HttpStatus.NOT_FOUND, "존재하지 않는 주문입니다."),
    NO_ORDER_DETAILS(HttpStatus.NOT_FOUND, "존재하지 않는 상세주문입니다."),
    INVALID_ORDER_STATUS(HttpStatus.FORBIDDEN, "주문을 취소할 수 있는 상태가 아닙니다."),
    RETURN_EXPIRED(HttpStatus.FORBIDDEN, "반품 가능 기간이 지났습니다."),

    // Product
    NO_PRODUCT(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다."),
    NO_PERMISSION_UPDATE(HttpStatus.FORBIDDEN, "이 게시글을 변경할 수 있는 권한이 없습니다."),
    NO_PERMISSION_DELETE(HttpStatus.FORBIDDEN, "이 게시글을 삭제할 수 있는 권한이 없습니다."),


    // WishList
    NO_WISHLIST(HttpStatus.NOT_FOUND, "존재하지 않는 위시리스트 입니다.");

    private final HttpStatus statusCode;
    private final String msg;

}

package com.study.productservice.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClientErrorCode {
    // Product
    ALREADY_DONE_POST(HttpStatus.I_AM_A_TEAPOT, "이미 완료된 게시글입니다."),
    NO_PERMISSION_UPDATE(HttpStatus.FORBIDDEN, "이 게시글을 변경할 수 있는 권한이 없습니다."),
    NO_PERMISSION_DELETE(HttpStatus.FORBIDDEN, "이 게시글을 삭제할 수 있는 권한이 없습니다."),
    NO_PRODUCT(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다.");

    private final HttpStatus statusCode;
    private final String msg;
}

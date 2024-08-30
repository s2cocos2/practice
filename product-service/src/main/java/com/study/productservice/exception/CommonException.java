package com.study.productservice.exception;


import com.study.productservice.exception.errorcode.ClientErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommonException extends RuntimeException{

    private final ClientErrorCode errorCode;

    @Override
    public String getMessage(){
        return errorCode.getMsg();
    }
}

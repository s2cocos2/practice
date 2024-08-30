package com.study.memberservice.exception;

import com.study.memberservice.exception.errorcode.ClientErrorCode;
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

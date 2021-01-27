package com.re.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String errMsg){
        super(errMsg);
    }
}

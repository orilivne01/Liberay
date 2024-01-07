package com.example.libary.exception;

public class BaseException extends RuntimeException{
    String reason;
    public BaseException(String reason){
        super((reason));
        this.reason = reason;
    }
}

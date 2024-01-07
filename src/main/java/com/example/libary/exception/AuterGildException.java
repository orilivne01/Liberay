package com.example.libary.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class AuterGildException extends BaseException{
    public AuterGildException(String reason){
        super((reason));
    }
}

package com.example.libary.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookException extends RuntimeException{
    private  String resaon;
    public BookException(String resaon){
        super((resaon));
        this.resaon = resaon;
    }
}

package com.example.libary.exception;

public class BookNotFound extends BookException{
    public BookNotFound(String reason){
        super(reason);
    }
}

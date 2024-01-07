package com.example.libary.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookException extends BookException{
    private static final String AddBookError= "Add book error because = ";
    public AddBookException(String resaon){
        super(resaon);
    }
    public String getMessage(){
        return AddBookError+this.getResaon();
    }
}

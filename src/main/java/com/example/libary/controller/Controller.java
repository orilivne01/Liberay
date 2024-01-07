package com.example.libary.controller;

import com.example.libary.dao.Auter;
import com.example.libary.dao.AuterGild;
import com.example.libary.dao.Books;
import com.example.libary.exception.AuterGildNotFoundGildException;
import com.example.libary.exception.AuterNotFoundException;
import com.example.libary.exception.BaseException;
import com.example.libary.exception.BookNotFound;
import com.example.libary.repo.AuterGildRepo;
import com.example.libary.service.AuterGildService;
import com.example.libary.service.AuterService;
import com.example.libary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/libarey")
public class Controller {
    @Autowired
    BookService bookServices;
    @Autowired
    AuterService auterService;
    @Autowired
    AuterGildService auterGildService;
    @RequestMapping("/test")
    public String test(){
        return "libarey";
    }
    @RequestMapping("/addBook")
    public ResponseEntity<Books> addBook(@RequestParam(name="bookName")String bookName){
        ResponseEntity<Books> respone = new ResponseEntity<>(bookServices.addBook(bookName), HttpStatus.OK);
        return respone;
    }
    @RequestMapping("/findBookByName")
    public ResponseEntity<Books> findBookByName(@RequestParam(name="bookName")String bookName) throws BookNotFound {
        ResponseEntity<Books> respone = new ResponseEntity<>(bookServices.findBooksByName(bookName), HttpStatus.OK);
        return respone;
    }

    @RequestMapping("/addAuter")
    public ResponseEntity<Auter> addAuter(@RequestParam(name="firstName")String firstName,@RequestParam(name="lastName")String lastName) throws BookNotFound {
        ResponseEntity<Auter> respone = new ResponseEntity<>(auterService.addAuter(firstName,lastName), HttpStatus.OK);
        return respone;
    }

    @RequestMapping("/addBookWithAuter")
    public ResponseEntity<Books> addBookWithAuter(@RequestParam(name="bookName")String bookName,@RequestParam(name="auterFirstName")String auterFirstName,@RequestParam(name="auterLastName")String auterLastName){
        ResponseEntity<Books> respone = new ResponseEntity<>(auterService.addBookWithAuter(bookName,auterFirstName,auterLastName), HttpStatus.OK);
        return respone;
    }


    @RequestMapping("/createAutoGild")
    public ResponseEntity<AuterGild> createAutoGild(@RequestParam(name="name")String name){
        return new ResponseEntity<AuterGild>(auterGildService.createAuterGild(name),HttpStatus.OK);
    }

    @RequestMapping("/addAuterToGild")
    public ResponseEntity<AuterGild> addAuterToGild(@RequestParam(name="auterFirstName")String auterFirstName,@RequestParam(name="auterLastName")String auterLastName,@RequestParam(name="gildName")String gildName) throws BaseException {
        return new ResponseEntity<AuterGild>(auterService.addAuterToGild(auterFirstName,auterLastName,gildName),HttpStatus.OK);
    }

    @ExceptionHandler(BookNotFound.class)
    public ResponseEntity<Object> handleResourceNotFoundException(BookNotFound ex) {
        //log.error("Resource not found", ex);

        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AuterNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(AuterNotFoundException ex) {
        //log.error("Resource not found", ex);

        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AuterGildNotFoundGildException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(AuterGildNotFoundGildException ex) {
        //log.error("Resource not found", ex);

        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

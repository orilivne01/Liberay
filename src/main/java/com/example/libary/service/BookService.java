package com.example.libary.service;


import com.example.libary.dao.Books;
import com.example.libary.exception.AddBookException;
import com.example.libary.exception.BookNotFound;
import com.example.libary.repo.AuterGildAuterRepo;
import com.example.libary.repo.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service()
public class BookService {

    @Autowired
    BooksRepo booksRepo;
    @Autowired
    AuterGildAuterRepo auterGildAuterRepo;
    public Books addBook(String bookName) throws AddBookException {
        Books books = new Books(bookName);
        try {
            books = booksRepo.save(books);
        }
        catch(Exception e){
            throw createAddBookException("fail in save bookName = "+bookName);
        }
        return books;
    }

    public Books findBooksByName(String bookName){
        auterGildAuterRepo.getAuterGildAuterOrder();
        Optional<Books> books= booksRepo.findBookByBookName(bookName);
        if(books.isPresent())
            return books.get();
        else
            throw new BookNotFound("Fail find book with name = "+bookName);
    }

    private AddBookException createAddBookException(String bookName){
        return new AddBookException(bookName);
    }
}

package com.example.libary.service;

import com.example.libary.dao.Auter;
import com.example.libary.dao.AuterGild;
import com.example.libary.dao.Books;
import com.example.libary.exception.AuterGildNotFoundGildException;
import com.example.libary.exception.AuterNotFoundException;
import com.example.libary.exception.BaseException;
import com.example.libary.repo.AuterGildAuterRepo;
import com.example.libary.repo.AuterGildRepo;
import com.example.libary.repo.AuterRepo;
import com.example.libary.repo.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Service
public class AuterService {
    @Autowired
    AuterRepo auterRepo;
    @Autowired
    BooksRepo booksRepo;

    @Autowired
    AuterGildRepo auterGildRepo;

    @Autowired
    AuterGildAuterRepo auterGildAuterRepo;
    public Auter addAuter(String firstName,String lastName){
        Auter auter = new Auter();
        auter.setFirstName(firstName);
        auter.setLastName(lastName);
        return auterRepo.save(auter);
    }
    public Auter findAuterByFirstNameAndLastName(String firstName,String lastName){
        Optional<Auter> auter = auterRepo.findAuterByFirstNameAndLastName(firstName,lastName);
        if(auter.isPresent())
            return auter.get();
        else
            throw new AuterNotFoundException("Auter with first name = "+firstName+" last name = "+lastName+" not found");
    }

    public Books addBookWithAuter(String bookName, String auterFirstName, String auterLastName) {
        Optional<Auter> auter = auterRepo.findAuterByFirstNameAndLastName(auterFirstName,auterLastName);
        if(auter.isEmpty())
            throw new AuterNotFoundException("Auter with first name = "+auterFirstName+" last name = "+auterLastName+" not found");
        else{
            Books books = new Books();
            books.setAuter(auter.get());
            books.setBookName(bookName);
            auter.get().getBooksList().add(books);
            auterRepo.save(auter.get());
            return  booksRepo.save(books);
        }

    }

    public AuterGild addAuterToGild(String auterFirstName, String auterLastName, String gildName) throws BaseException {
        Optional<Auter> auter = auterRepo.findAuterByFirstNameAndLastName(auterFirstName,auterLastName);
        if(auter.isEmpty())
            throw new AuterNotFoundException("Auter with first name = "+auterFirstName+" last name = "+auterLastName+" not found");
        Optional<AuterGild> auterGild = auterGildRepo.findByName(gildName);
        if(auterGild.isEmpty())
            throw new AuterGildNotFoundGildException("Auter gild with name = "+gildName+" not found");
        auter.get().getAuterGilds().add(auterGild.get());
        auterGild.get().getAuters().add(auter.get());
        auterRepo.save(auter.get());
        return auterGildRepo.save(auterGild.get());
    }

}

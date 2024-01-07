package com.example.libary.service;

import com.example.libary.dao.AuterGild;
import com.example.libary.repo.AuterGildRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuterGildService {
    @Autowired
    AuterGildRepo auterGildRepo;
    public AuterGild createAuterGild(String name){
        AuterGild auterGild = new AuterGild();
        auterGild.setName(name);
        return auterGildRepo.save(auterGild);
    }
}

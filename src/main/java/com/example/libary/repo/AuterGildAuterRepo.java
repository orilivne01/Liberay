package com.example.libary.repo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AuterGildAuterRepo {
    @PersistenceContext
    private EntityManager entityManager;
    public String[][] getAuterGildAuterOrder(){
        Query query = entityManager.createQuery("select g.id,count(a.id) from Auter a,AuterGild g group by g.id order by count(a.id) desc");
        List<Object[]> list = query.getResultList();
        String[][] resault = new String[list.size()][];
        for(int index=0;index<list.size();index++){
            resault[index] = new String[2];
            resault[index][0] = String.valueOf(list.get(index)[0]);
            resault[index][1] = String.valueOf(list.get(index)[1]);
        }
        return resault;
    }
}

package com.example.libary.repo;


import com.example.libary.dao.Auter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuterRepo extends JpaRepository<Auter,Long> {
    Optional<Auter> findAuterByFirstNameAndLastName(String firstName,String lastName);
}

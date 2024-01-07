package com.example.libary.repo;

import com.example.libary.dao.AuterGild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuterGildRepo extends JpaRepository<AuterGild,Long> {
    Optional<AuterGild> findByName(String gildName);
}

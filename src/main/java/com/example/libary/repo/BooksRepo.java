package com.example.libary.repo;

import com.example.libary.dao.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepo extends JpaRepository<Books,Long> {
    Optional<Books> findBookByBookName(String bookName);
}

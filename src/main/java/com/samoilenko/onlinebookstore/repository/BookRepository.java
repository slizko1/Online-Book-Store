package com.samoilenko.onlinebookstore.repository;

import com.samoilenko.onlinebookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

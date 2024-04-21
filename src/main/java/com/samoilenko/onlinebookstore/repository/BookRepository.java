package com.samoilenko.onlinebookstore.repository;

import java.util.List;
import com.samoilenko.onlinebookstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}

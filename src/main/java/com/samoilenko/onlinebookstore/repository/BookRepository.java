package com.samoilenko.onlinebookstore.repository;

import com.samoilenko.onlinebookstore.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}

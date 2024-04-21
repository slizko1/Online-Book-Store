package com.samoilenko.onlinebookstore.service;

import com.samoilenko.onlinebookstore.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}

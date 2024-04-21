package com.samoilenko.onlinebookstore.service;

import java.util.List;
import com.samoilenko.onlinebookstore.model.Book;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();
}

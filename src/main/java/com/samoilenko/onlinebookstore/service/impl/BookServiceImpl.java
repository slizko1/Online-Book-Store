package com.samoilenko.onlinebookstore.service.impl;

import com.samoilenko.onlinebookstore.model.Book;
import com.samoilenko.onlinebookstore.repository.BookRepository;
import com.samoilenko.onlinebookstore.service.BookService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

}

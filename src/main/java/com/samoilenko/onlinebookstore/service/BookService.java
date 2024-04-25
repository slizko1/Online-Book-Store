package com.samoilenko.onlinebookstore.service;

import com.samoilenko.onlinebookstore.dto.BookDto;
import com.samoilenko.onlinebookstore.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto createBook(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findBookById(Long id);
}

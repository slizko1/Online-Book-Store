package com.samoilenko.onlinebookstore.service;

import com.samoilenko.onlinebookstore.dto.BookDto;
import com.samoilenko.onlinebookstore.dto.BookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto createBook(BookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findBookById(Long id);

    void deleteById(Long id);

    BookDto update(Long id, BookRequestDto bookRequestDto);
}

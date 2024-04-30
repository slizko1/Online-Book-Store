package com.samoilenko.onlinebookstore.service.impl;

import com.samoilenko.onlinebookstore.dto.BookDto;
import com.samoilenko.onlinebookstore.dto.BookRequestDto;
import com.samoilenko.onlinebookstore.exception.EntityNotFoundException;
import com.samoilenko.onlinebookstore.mapper.BookMapper;
import com.samoilenko.onlinebookstore.model.Book;
import com.samoilenko.onlinebookstore.repository.BookRepository;
import com.samoilenko.onlinebookstore.service.BookService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto createBook(BookRequestDto requestDto) {
        Book savedBook = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(savedBook));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findBookById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't get book by id " + id)
        ));
    }

    @Override
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book with id " + id + " not found");
        }
        bookRepository.deleteById(id);
    }

    public BookDto update(Long id, BookRequestDto bookRequestDto) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book with id " + id + " not found");
        }
        Book updatedBook = bookMapper.toModel(bookRequestDto);
        updatedBook.setId(id);
        return bookMapper.toDto(bookRepository.save(updatedBook));
    }

}

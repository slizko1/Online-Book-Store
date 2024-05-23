package com.samoilenko.onlinebookstore.service.impl;

import com.samoilenko.onlinebookstore.dto.bookdtos.BookDto;
import com.samoilenko.onlinebookstore.dto.bookdtos.BookDtoWithoutCategoryIds;
import com.samoilenko.onlinebookstore.dto.bookdtos.BookRequestDto;
import com.samoilenko.onlinebookstore.exception.EntityNotFoundException;
import com.samoilenko.onlinebookstore.mapper.BookMapper;
import com.samoilenko.onlinebookstore.model.Book;
import com.samoilenko.onlinebookstore.repository.BookRepository;
import com.samoilenko.onlinebookstore.service.BookService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryServiceImpl categoryService;

    @Override
    public BookDto createBook(BookRequestDto requestDto) {
        validateCategories(requestDto.getCategoryIds());
        Book savedBook = bookMapper.toEntity(requestDto);
        return bookMapper.toDto(bookRepository.save(savedBook));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
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
        validateId(id);
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto update(Long id, BookRequestDto bookRequestDto) {
        validateId(id);
        validateCategories(bookRequestDto.getCategoryIds());
        Book updatedBook = bookMapper.toEntity(bookRequestDto);
        updatedBook.setId(id);
        return bookMapper.toDto(bookRepository.save(updatedBook));
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findBooksByCategoryId(Pageable pageable, Long id) {
        categoryService.getById(id);
        return bookRepository.findAllByCategoryId(pageable, id).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }

    private void validateId(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book with id " + id + " not found");
        }
    }

    private void validateCategories(Set<Long> categoryIds) {
        var existingCategories = categoryService.findAllByIds(categoryIds);
        if (existingCategories.size() < categoryIds.size()) {
            throw new EntityNotFoundException("Some categories not found");
        }
    }
}

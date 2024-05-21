package com.samoilenko.onlinebookstore.mapper;

import com.samoilenko.onlinebookstore.config.MapperConfig;
import com.samoilenko.onlinebookstore.dto.bookdtos.BookDto;
import com.samoilenko.onlinebookstore.dto.bookdtos.BookDtoWithoutCategoryIds;
import com.samoilenko.onlinebookstore.dto.bookdtos.BookRequestDto;
import com.samoilenko.onlinebookstore.model.Book;
import com.samoilenko.onlinebookstore.model.Category;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    @Mapping(target = "categories", ignore = true)
    BookDto toDto(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategories(book.getCategories().stream()
                .map(category -> category.getId())
                .collect(Collectors.toSet()));
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Book toEntity(BookRequestDto requestDto);

    @AfterMapping
    default void mapCategories(@MappingTarget Book book, BookRequestDto requestDto) {
        Set<Category> set = requestDto.getCategoryIds().stream()
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                })
                .collect(Collectors.toSet());
        book.setCategories(set);
    }

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);
}

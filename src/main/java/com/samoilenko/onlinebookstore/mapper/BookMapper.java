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

//    @AfterMapping
//    default void mapCategories(BookRequestDto bookDto, @MappingTarget Book book) {
//        Set<Category> categories = bookDto.getCategoryIds().stream()
//                .map(id -> entityManager.getReference(Category.class, id))
//                .collect(Collectors.toSet());
//        book.setCategories(categories);
//    }

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);
}

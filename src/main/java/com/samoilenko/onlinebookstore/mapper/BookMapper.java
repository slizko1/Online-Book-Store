package com.samoilenko.onlinebookstore.mapper;

import com.samoilenko.onlinebookstore.config.MapperConfig;
import com.samoilenko.onlinebookstore.dto.BookDto;
import com.samoilenko.onlinebookstore.dto.BookRequestDto;
import com.samoilenko.onlinebookstore.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Book toModel(BookRequestDto requestDto);
}

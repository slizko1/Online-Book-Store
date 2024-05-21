package com.samoilenko.onlinebookstore.mapper;

import com.samoilenko.onlinebookstore.config.MapperConfig;
import com.samoilenko.onlinebookstore.dto.CategoryDto;
import com.samoilenko.onlinebookstore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Category toEntity(CategoryDto categoryDto);
}

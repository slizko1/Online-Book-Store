package com.samoilenko.onlinebookstore.service;

import com.samoilenko.onlinebookstore.dto.categorydtos.CategoryDto;
import com.samoilenko.onlinebookstore.dto.categorydtos.CategoryRequestDto;
import com.samoilenko.onlinebookstore.model.Category;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto save(CategoryRequestDto requestDto);

    CategoryDto update(Long id, CategoryRequestDto requestDto);

    void deleteById(Long id);

    List<Category> findAllByIds(Set<Long> categoryIds);
}

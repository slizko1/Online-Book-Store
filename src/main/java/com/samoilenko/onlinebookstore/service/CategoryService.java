package com.samoilenko.onlinebookstore.service;

import com.samoilenko.onlinebookstore.dto.categorydtos.CategoryDto;
import com.samoilenko.onlinebookstore.model.Category;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    void deleteById(Long id);

    List<Category> findAllByIds(Set<Long> categoryIds);
}

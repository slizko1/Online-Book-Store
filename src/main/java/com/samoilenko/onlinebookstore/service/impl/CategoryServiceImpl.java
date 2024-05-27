package com.samoilenko.onlinebookstore.service.impl;

import com.samoilenko.onlinebookstore.dto.categorydtos.CategoryDto;
import com.samoilenko.onlinebookstore.dto.categorydtos.CategoryRequestDto;
import com.samoilenko.onlinebookstore.exception.EntityNotFoundException;
import com.samoilenko.onlinebookstore.mapper.CategoryMapper;
import com.samoilenko.onlinebookstore.model.Category;
import com.samoilenko.onlinebookstore.repository.CategoryRepository;
import com.samoilenko.onlinebookstore.service.CategoryService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Cant get category by id = " + id)
        ));
    }

    @Override
    public CategoryDto save(CategoryRequestDto requestDto) {
        Category savedCategory = categoryMapper.toEntity(requestDto);
        return categoryMapper.toDto(categoryRepository.save(savedCategory));
    }

    @Override
    public CategoryDto update(Long id, CategoryRequestDto requestDto) {
        validateId(id);
        Category updatedCategory = categoryMapper.toEntity(requestDto);
        updatedCategory.setId(id);
        return categoryMapper.toDto(categoryRepository.save(updatedCategory));
    }

    @Override
    public void deleteById(Long id) {
        validateId(id);
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAllByIds(Set<Long> categoryIds) {
        return categoryRepository.findAllById(categoryIds);
    }

    private void validateId(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
    }
}

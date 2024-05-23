package com.samoilenko.onlinebookstore.dto.bookdtos;

import java.math.BigDecimal;

public record BookDtoWithoutCategoryIds(
        Long id,
        String title,
        BigDecimal price,
        String author,
        String isbn,
        String description,
        String coverImage
) {}

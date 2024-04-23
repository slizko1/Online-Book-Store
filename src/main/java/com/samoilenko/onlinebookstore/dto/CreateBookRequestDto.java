package com.samoilenko.onlinebookstore.dto;

import java.math.BigDecimal;

public class CreateBookRequestDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String author;
    private String isbn;
    private String description;
    private String coverImage;
}

package com.samoilenko.onlinebookstore.dto.bookdtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;

@Data
public class BookRequestDto {
    @NotBlank
    private String title;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotBlank
    private String author;
    @NotNull
    private String isbn;
    private String description;
    private String coverImage;
    @NotNull
    private Set<Long> categoryIds;
}

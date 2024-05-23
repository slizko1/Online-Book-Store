package com.samoilenko.onlinebookstore.dto.categorydtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank
    private String name;
    private String description;
}

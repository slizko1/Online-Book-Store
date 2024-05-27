package com.samoilenko.onlinebookstore.dto.orderdtos;

import com.samoilenko.onlinebookstore.model.Order;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusRequestDto {
    @NotNull
    private Order.Status status;
}

package com.ecommerce.backend.dto;

import com.ecommerce.backend.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class OrderStatusRequestDTO {

    @NotNull(message = "El estado es obligatorio")
    private OrderStatus status;

}

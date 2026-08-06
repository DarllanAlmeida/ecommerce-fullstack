package com.ecommerce.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {

    @NotNull(message = "El cliente es obligatorio")
    private Long customerId;

    @NotNull(message = "La dirección es obligatoria")
    private Long addressId;

}

package com.ecommerce.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SizeRequestDTO {

    @NotBlank(message = "La talla es obligatoria")
    private String name;

    private String description;

}

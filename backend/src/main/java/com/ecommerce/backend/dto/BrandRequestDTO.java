package com.ecommerce.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BrandRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    private String logoUrl;

    private String country;

    private String description;

}

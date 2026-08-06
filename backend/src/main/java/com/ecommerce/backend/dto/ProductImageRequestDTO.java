package com.ecommerce.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageRequestDTO {

    @NotBlank(message = "La URL es obligatoria")
    private String imageUrl;

    private String altText;

    @NotNull(message = "El orden es obligatorio")
    private Integer displayOrder;

    @NotNull(message = "El producto es obligatorio")
    private Long productId;

}

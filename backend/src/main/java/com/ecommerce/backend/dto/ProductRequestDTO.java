package com.ecommerce.backend.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {



    @NotBlank(message = "El nombre es obligatorio")
    private String name;




    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = false
    )
    private BigDecimal price;




    @NotBlank(message = "La descripción es obligatoria")
    private String description;




    @NotNull(message = "El stock es obligatorio")
    @Min(0)
    private Integer stock;




    private String imageUrl;





    @NotNull(message = "La marca es obligatoria")
    private Long brandId;





    @NotNull(message = "La categoría es obligatoria")
    private Long categoryId;





    private List<ProductVariantRequestDTO> variants;



}

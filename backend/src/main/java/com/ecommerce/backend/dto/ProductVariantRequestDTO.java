package com.ecommerce.backend.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.math.BigDecimal;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantRequestDTO {


    private Long id;



    @NotBlank(message = "El SKU es obligatorio")
    private String sku;



    @NotBlank(message = "La talla es obligatoria")
    private String size;



    @NotBlank(message = "El color es obligatorio")
    private String color;



    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = false
    )
    private BigDecimal price;



    @NotNull(message = "El stock es obligatorio")
    @Min(0)
    private Integer stock;



}

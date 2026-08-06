package com.ecommerce.backend.dto;

import lombok.*;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantResponseDTO {


    private Long id;


    private String sku;


    private String size;


    private String color;


    private BigDecimal price;


    private Integer stock;


}

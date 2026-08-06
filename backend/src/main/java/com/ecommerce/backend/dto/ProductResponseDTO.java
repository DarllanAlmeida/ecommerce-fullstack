package com.ecommerce.backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {


    private Long id;


    private String name;


    private BigDecimal price;


    private String description;


    private Integer stock;


    private String imageUrl;


    private Long brandId;


    private String brandName;


    private Long categoryId;


    private String categoryName;


    private List<ProductVariantResponseDTO> variants;


}

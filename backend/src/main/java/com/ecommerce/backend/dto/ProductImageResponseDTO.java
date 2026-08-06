package com.ecommerce.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageResponseDTO {

    private Long id;

    private String imageUrl;

    private String altText;

    private Integer displayOrder;

    private Boolean active;

    private Long productId;

    private String productName;

}

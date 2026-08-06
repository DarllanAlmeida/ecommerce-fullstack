package com.ecommerce.backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponseDTO {

    private Long id;

    private Long productVariantId;

    private String productName;

    private String size;

    private String color;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subtotal;

}

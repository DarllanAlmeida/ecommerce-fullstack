package com.ecommerce.backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDTO {

    private Long id;

    private String productName;

    private String sku;

    private String size;

    private String color;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subtotal;

}
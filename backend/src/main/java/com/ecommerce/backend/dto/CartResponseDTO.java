package com.ecommerce.backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDTO {

    private Long id;

    private Long customerId;

    private String customerName;

    private BigDecimal subtotal;

    private BigDecimal discount;

    private BigDecimal shipping;

    private BigDecimal total;

    private List<CartItemResponseDTO> items;

}

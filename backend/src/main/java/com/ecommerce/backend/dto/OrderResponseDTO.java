package com.ecommerce.backend.dto;

import com.ecommerce.backend.model.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {

    private Long id;

    private Long customerId;

    private String customerName;

    private LocalDateTime createdAt;

    private BigDecimal subtotal;

    private BigDecimal discount;

    private BigDecimal shipping;

    private BigDecimal total;

    private OrderStatus status;

    private List<OrderItemResponseDTO> items;

}
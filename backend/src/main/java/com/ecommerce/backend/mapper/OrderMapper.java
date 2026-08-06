package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.OrderItemResponseDTO;
import com.ecommerce.backend.dto.OrderResponseDTO;
import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.model.OrderItem;

import java.util.stream.Collectors;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderResponseDTO toDTO(Order order) {

        return OrderResponseDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .customerName(
                        order.getCustomer().getFirstName() + " " +
                                order.getCustomer().getLastName()
                )
                .createdAt(order.getCreatedAt())
                .subtotal(order.getSubtotal())
                .discount(order.getDiscount())
                .shipping(order.getShipping())
                .total(order.getTotal())
                .status(order.getStatus())
                .items(
                        order.getItems()
                                .stream()
                                .map(OrderMapper::toItemDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static OrderItemResponseDTO toItemDTO(OrderItem item) {

        return OrderItemResponseDTO.builder()
                .id(item.getId())
                .productName(item.getProductVariant().getProduct().getName())
                .sku(item.getProductVariant().getSku())
                .size(item.getProductVariant().getSize())
                .color(item.getProductVariant().getColor())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .subtotal(item.getSubtotal())
                .build();
    }

}

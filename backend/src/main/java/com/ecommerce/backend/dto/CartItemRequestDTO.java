package com.ecommerce.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemRequestDTO {

    @NotNull
    private Long customerId;

    @NotNull
    private Long productVariantId;

    @NotNull
    @Min(1)
    private Integer quantity;

}

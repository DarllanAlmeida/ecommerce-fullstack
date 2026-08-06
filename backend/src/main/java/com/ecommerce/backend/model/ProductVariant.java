package com.ecommerce.backend.model;


import jakarta.persistence.*;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;


import java.math.BigDecimal;



@Entity
@Table(name = "product_variants")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Builder

public class ProductVariant {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;





    @NotBlank(message = "El SKU es obligatorio")
    @Column(nullable = false, unique = true)
    private String sku;





    @NotBlank(message = "La talla es obligatoria")
    @Column(nullable = false, length = 10)
    private String size;





    @NotBlank(message = "El color es obligatorio")
    @Column(nullable = false, length = 50)
    private String color;





    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = false
    )
    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal price;





    @NotNull(message = "El stock es obligatorio")
    @Min(0)
    @Column(nullable = false)
    private Integer stock;





    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;





    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(
            name = "product_id",
            nullable = false
    )

    private Product product;



}
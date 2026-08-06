package com.ecommerce.backend.model;


import jakarta.persistence.*;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import lombok.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "products")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Builder

public class Product {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;





    @NotBlank(message = "El nombre es obligatorio")

    @Column(nullable = false)

    private String name;







    @NotBlank(message = "La descripción es obligatoria")

    @Column(nullable = false, length = 1000)

    private String description;







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







    @Column(length = 1000)

    private String imageUrl;








    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(
            name="brand_id",
            nullable=false
    )

    private Brand brand;








    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(
            name="category_id",
            nullable=false
    )

    private Category category;








    @OneToMany(

            mappedBy = "product",

            cascade = CascadeType.ALL,

            orphanRemoval = true,

            fetch = FetchType.LAZY

    )

    @Builder.Default

    private List<ProductImage> images =
            new ArrayList<>();









    @OneToMany(

            mappedBy = "product",

            cascade = CascadeType.ALL,

            orphanRemoval = true,

            fetch = FetchType.EAGER

    )

    @Builder.Default

    private List<ProductVariant> variants =
            new ArrayList<>();









    @Builder.Default

    @Column(nullable = false)

    private Boolean active = true;




}
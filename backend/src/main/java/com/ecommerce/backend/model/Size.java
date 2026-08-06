package com.ecommerce.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "sizes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La talla es obligatoria")
    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @Column(length = 100)
    private String description;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

}

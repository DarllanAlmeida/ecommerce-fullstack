package com.ecommerce.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La calle es obligatoria")
    @Column(nullable = false)
    private String street;

    @Column(length = 255)
    private String addressLine2;

    @NotBlank(message = "La ciudad es obligatoria")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "La provincia es obligatoria")
    @Column(nullable = false)
    private String province;

    @NotBlank(message = "El código postal es obligatorio")
    @Column(nullable = false)
    private String postalCode;

    @NotBlank(message = "El país es obligatorio")
    @Column(nullable = false)
    private String country;

    @Column(length = 255)
    private String reference;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isDefault = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}

package com.ecommerce.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(nullable = false, length = 100)
    private String lastName;

    @Email(message = "El email no es válido")
    @NotBlank(message = "El email es obligatorio")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false)
    private String password;

    @Column(length = 20)
    private String phone;

    @Column(unique = true, length = 100)
    private String username;

    @Column(length = 1000)
    private String profileImage;

    @Builder.Default
    @Column(nullable = false)
    private Boolean emailVerified = false;

    // CAMBIO IMPORTANTE: LAZY -> EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

}

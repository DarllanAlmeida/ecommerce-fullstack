package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    // Buscar un rol por nombre
    Optional<Role> findByName(String name);

    // Comprobar si existe un rol (lo usa RoleServiceImpl)
    boolean existsByNameIgnoreCase(String name);

}

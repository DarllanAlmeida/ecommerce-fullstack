package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size, Long> {

    List<Size> findByActiveTrue();

    Optional<Size> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

}

package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductId(Long productId);

    List<ProductImage> findByProductIdAndActiveTrueOrderByDisplayOrderAsc(Long productId);

}

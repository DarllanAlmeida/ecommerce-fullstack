package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.ProductImageRequestDTO;
import com.ecommerce.backend.dto.ProductImageResponseDTO;
import com.ecommerce.backend.service.ProductImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-images")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping
    public List<ProductImageResponseDTO> getAll() {
        return productImageService.getAll();
    }

    @GetMapping("/{id}")
    public ProductImageResponseDTO getById(@PathVariable Long id) {
        return productImageService.getById(id);
    }

    @GetMapping("/product/{productId}")
    public List<ProductImageResponseDTO> getByProduct(
            @PathVariable Long productId) {

        return productImageService.getByProduct(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductImageResponseDTO create(
            @Valid @RequestBody ProductImageRequestDTO dto) {

        return productImageService.create(dto);
    }

    @PutMapping("/{id}")
    public ProductImageResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody ProductImageRequestDTO dto) {

        return productImageService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        productImageService.delete(id);
    }
}

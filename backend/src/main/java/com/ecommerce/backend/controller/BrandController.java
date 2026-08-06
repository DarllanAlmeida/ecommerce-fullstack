package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.BrandRequestDTO;
import com.ecommerce.backend.dto.BrandResponseDTO;
import com.ecommerce.backend.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BrandController {

    private final BrandService service;

    @GetMapping
    public List<BrandResponseDTO> getAllBrands() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BrandResponseDTO getBrandById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandResponseDTO createBrand(@Valid @RequestBody BrandRequestDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public BrandResponseDTO updateBrand(@PathVariable Long id,
                                        @Valid @RequestBody BrandRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable Long id) {
        service.delete(id);
    }
}
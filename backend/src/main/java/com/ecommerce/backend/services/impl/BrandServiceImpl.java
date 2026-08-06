package com.ecommerce.backend.services.impl;

import com.ecommerce.backend.dto.BrandRequestDTO;
import com.ecommerce.backend.dto.BrandResponseDTO;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.model.Brand;
import com.ecommerce.backend.repository.BrandRepository;
import com.ecommerce.backend.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;

    @Override
    public List<BrandResponseDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public BrandResponseDTO findById(Long id) {

        Brand brand = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada"));

        return toResponse(brand);
    }

    @Override
    public BrandResponseDTO create(BrandRequestDTO dto) {

        if (repository.existsByNameIgnoreCase(dto.getName())) {
            throw new IllegalArgumentException("La marca ya existe");
        }

        Brand brand = repository.save(toEntity(dto));

        return toResponse(brand);
    }

    @Override
    public BrandResponseDTO update(Long id, BrandRequestDTO dto) {

        Brand brand = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada"));

        brand.setName(dto.getName());
        brand.setLogoUrl(dto.getLogoUrl());
        brand.setCountry(dto.getCountry());
        brand.setDescription(dto.getDescription());

        repository.save(brand);

        return toResponse(brand);
    }

    @Override
    public void delete(Long id) {

        Brand brand = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada"));

        brand.setActive(false);

        repository.save(brand);
    }

    @Override
    public Brand toEntity(BrandRequestDTO dto) {

        return Brand.builder()
                .name(dto.getName())
                .logoUrl(dto.getLogoUrl())
                .country(dto.getCountry())
                .description(dto.getDescription())
                .build();
    }

    @Override
    public BrandResponseDTO toResponse(Brand brand) {

        return BrandResponseDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .logoUrl(brand.getLogoUrl())
                .country(brand.getCountry())
                .description(brand.getDescription())
                .active(brand.getActive())
                .build();
    }
}

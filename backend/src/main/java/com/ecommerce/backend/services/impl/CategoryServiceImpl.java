package com.ecommerce.backend.services.impl;

import com.ecommerce.backend.dto.CategoryRequestDTO;
import com.ecommerce.backend.dto.CategoryResponseDTO;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.repository.CategoryRepository;
import com.ecommerce.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAll() {

        return repository.findByActiveTrue()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO findById(Long id) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoría no encontrada"));

        return toResponse(category);
    }

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO dto) {

        if (repository.existsByNameIgnoreCase(dto.getName())) {
            throw new IllegalArgumentException("La categoría ya existe");
        }

        Category category = repository.save(toEntity(dto));

        return toResponse(category);
    }

    @Override
    public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoría no encontrada"));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        Category updated = repository.save(category);

        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoría no encontrada"));

        category.setActive(false);

        repository.save(category);
    }

    @Override
    public Category toEntity(CategoryRequestDTO dto) {

        return Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    @Override
    public CategoryResponseDTO toResponse(Category category) {

        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .active(category.getActive())
                .build();
    }
}

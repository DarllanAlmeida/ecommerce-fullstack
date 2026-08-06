package com.ecommerce.backend.services.impl;

import com.ecommerce.backend.dto.ProductImageRequestDTO;
import com.ecommerce.backend.dto.ProductImageResponseDTO;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.mapper.ProductImageMapper;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.ProductImage;
import com.ecommerce.backend.repository.ProductImageRepository;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository imageRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductImageResponseDTO> getAll() {

        return imageRepository.findAll()
                .stream()
                .map(ProductImageMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductImageResponseDTO getById(Long id) {

        ProductImage image = imageRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Imagen no encontrada"));

        return ProductImageMapper.toDTO(image);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductImageResponseDTO> getByProduct(Long productId) {

        return imageRepository
                .findByProductIdAndActiveTrueOrderByDisplayOrderAsc(productId)
                .stream()
                .map(ProductImageMapper::toDTO)
                .toList();
    }

    @Override
    public ProductImageResponseDTO create(ProductImageRequestDTO dto) {

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Producto no encontrado"));

        ProductImage image = ProductImageMapper.toEntity(dto, product);

        return ProductImageMapper.toDTO(
                imageRepository.save(image)
        );
    }

    @Override
    public ProductImageResponseDTO update(Long id,
                                          ProductImageRequestDTO dto) {

        ProductImage image = imageRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Imagen no encontrada"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Producto no encontrado"));

        image.setImageUrl(dto.getImageUrl());
        image.setAltText(dto.getAltText());
        image.setDisplayOrder(dto.getDisplayOrder());
        image.setProduct(product);

        return ProductImageMapper.toDTO(
                imageRepository.save(image)
        );
    }

    @Override
    public void delete(Long id) {

        ProductImage image = imageRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Imagen no encontrada"));

        image.setActive(false);

        imageRepository.save(image);
    }
}

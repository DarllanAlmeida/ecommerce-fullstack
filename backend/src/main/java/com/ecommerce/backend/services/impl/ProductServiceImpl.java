package com.ecommerce.backend.services.impl;


import com.ecommerce.backend.dto.ProductRequestDTO;
import com.ecommerce.backend.dto.ProductResponseDTO;

import com.ecommerce.backend.exception.ResourceNotFoundException;

import com.ecommerce.backend.mapper.ProductMapper;

import com.ecommerce.backend.model.Brand;
import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.ProductVariant;

import com.ecommerce.backend.repository.BrandRepository;
import com.ecommerce.backend.repository.CategoryRepository;
import com.ecommerce.backend.repository.ProductRepository;

import com.ecommerce.backend.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import java.util.List;



@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {



    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;






    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAll() {


        return productRepository.findByActiveTrue()

                .stream()

                .map(this::toResponse)

                .toList();

    }








    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getById(Long id) {



        Product product =

                productRepository.findById(id)

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Producto no encontrado"
                                ));



        return toResponse(product);

    }









    @Override
    public ProductResponseDTO save(ProductRequestDTO dto) {



        Brand brand =
                brandRepository.findById(dto.getBrandId())

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Marca no encontrada"
                                ));




        Category category =
                categoryRepository.findById(dto.getCategoryId())

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Categoría no encontrada"
                                ));







        Product product =
                ProductMapper.toEntity(
                        dto,
                        brand,
                        category
                );



        product.setActive(true);






        if(dto.getVariants()!=null){



            dto.getVariants()

                    .forEach(v -> {



                        ProductVariant variant =
                                new ProductVariant();



                        variant.setSku(v.getSku());

                        variant.setSize(v.getSize());

                        variant.setColor(v.getColor());

                        variant.setPrice(v.getPrice());

                        variant.setStock(v.getStock());

                        variant.setActive(true);

                        variant.setProduct(product);



                        product.getVariants()
                                .add(variant);



                    });



        }






        Product saved =
                productRepository.save(product);



        return toResponse(saved);


    }









    @Override
    public ProductResponseDTO update(
            Long id,
            ProductRequestDTO dto
    ) {



        Product product =

                productRepository.findById(id)

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Producto no encontrado"
                                ));







        Brand brand =

                brandRepository.findById(dto.getBrandId())

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Marca no encontrada"
                                ));






        Category category =

                categoryRepository.findById(dto.getCategoryId())

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Categoría no encontrada"
                                ));









        product.setName(dto.getName());

        product.setDescription(dto.getDescription());

        product.setPrice(dto.getPrice());

        product.setStock(dto.getStock());

        product.setImageUrl(dto.getImageUrl());

        product.setBrand(brand);

        product.setCategory(category);









        // ==========================
        // ACTUALIZAR VARIANTES
        // ==========================



        if(dto.getVariants()!=null){



            for(var v : dto.getVariants()){



                ProductVariant variant = null;





                if(v.getId()!=null){



                    variant =
                            product.getVariants()

                                    .stream()

                                    .filter(existing ->
                                            existing.getId()
                                                    .equals(v.getId())
                                    )

                                    .findFirst()

                                    .orElse(null);



                }







                // NUEVA VARIANTE


                if(variant==null){



                    variant =
                            new ProductVariant();



                    variant.setProduct(product);



                    product.getVariants()
                            .add(variant);



                }







                variant.setSku(
                        v.getSku()
                );


                variant.setSize(
                        v.getSize()
                );


                variant.setColor(
                        v.getColor()
                );


                variant.setPrice(
                        v.getPrice()
                );


                variant.setStock(
                        v.getStock()
                );


                variant.setActive(true);



            }



        }








        Product updated =

                productRepository.save(product);






        return toResponse(updated);



    }









    @Override
    public void delete(Long id) {



        Product product =

                productRepository.findById(id)

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Producto no encontrado"
                                ));



        product.setActive(false);



        productRepository.save(product);



    }









    @Override
    public Product toEntity(ProductRequestDTO dto) {


        throw new UnsupportedOperationException(
                "Utiliza ProductMapper.toEntity(dto, brand, category)"
        );


    }








    @Override
    public ProductResponseDTO toResponse(Product product) {


        return ProductMapper.toDTO(product);


    }


}

package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.SizeRequestDTO;
import com.ecommerce.backend.dto.SizeResponseDTO;
import com.ecommerce.backend.model.Size;

public class SizeMapper {

    private SizeMapper() {
    }

    public static Size toEntity(SizeRequestDTO dto) {

        return Size.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .active(true)
                .build();
    }

    public static SizeResponseDTO toDTO(Size size) {

        return SizeResponseDTO.builder()
                .id(size.getId())
                .name(size.getName())
                .description(size.getDescription())
                .active(size.getActive())
                .build();
    }

}

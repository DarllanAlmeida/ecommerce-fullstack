package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.RoleRequestDTO;
import com.ecommerce.backend.dto.RoleResponseDTO;
import com.ecommerce.backend.model.Role;

public class RoleMapper {

    private RoleMapper() {
    }

    public static Role toEntity(RoleRequestDTO dto) {

        return Role.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .active(true)
                .build();
    }

    public static RoleResponseDTO toDTO(Role role) {

        return RoleResponseDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .active(role.getActive())
                .build();
    }

}

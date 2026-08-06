package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.RoleRequestDTO;
import com.ecommerce.backend.dto.RoleResponseDTO;

import java.util.List;

public interface RoleService {

    List<RoleResponseDTO> getAll();

    RoleResponseDTO getById(Long id);

    RoleResponseDTO create(RoleRequestDTO dto);

    RoleResponseDTO update(Long id, RoleRequestDTO dto);

    void delete(Long id);

}
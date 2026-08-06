package com.ecommerce.backend.services.impl;

import com.ecommerce.backend.dto.RoleRequestDTO;
import com.ecommerce.backend.dto.RoleResponseDTO;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.mapper.RoleMapper;
import com.ecommerce.backend.model.Role;
import com.ecommerce.backend.repository.RoleRepository;
import com.ecommerce.backend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponseDTO> getAll() {

        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponseDTO getById(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rol no encontrado"));

        return RoleMapper.toDTO(role);
    }

    @Override
    public RoleResponseDTO create(RoleRequestDTO dto) {

        if (roleRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new IllegalArgumentException("El rol ya existe");
        }

        Role role = RoleMapper.toEntity(dto);

        return RoleMapper.toDTO(roleRepository.save(role));
    }

    @Override
    public RoleResponseDTO update(Long id, RoleRequestDTO dto) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rol no encontrado"));

        role.setName(dto.getName());
        role.setDescription(dto.getDescription());

        return RoleMapper.toDTO(roleRepository.save(role));
    }

    @Override
    public void delete(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rol no encontrado"));

        role.setActive(false);

        roleRepository.save(role);
    }

}
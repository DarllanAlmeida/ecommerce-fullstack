package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.RoleRequestDTO;
import com.ecommerce.backend.dto.RoleResponseDTO;
import com.ecommerce.backend.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<RoleResponseDTO> getAll() {
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    public RoleResponseDTO getById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponseDTO create(@Valid @RequestBody RoleRequestDTO dto) {
        return roleService.create(dto);
    }

    @PutMapping("/{id}")
    public RoleResponseDTO update(@PathVariable Long id,
                                  @Valid @RequestBody RoleRequestDTO dto) {
        return roleService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}

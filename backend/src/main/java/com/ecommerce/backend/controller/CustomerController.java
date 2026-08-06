package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.CustomerRequestDTO;
import com.ecommerce.backend.dto.CustomerResponseDTO;
import com.ecommerce.backend.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerResponseDTO> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @GetMapping("/email/{email}")
    public CustomerResponseDTO getByEmail(@PathVariable String email) {
        return customerService.getByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDTO create(
            @Valid @RequestBody CustomerRequestDTO dto) {

        return customerService.create(dto);
    }

    @PutMapping("/{id}")
    public CustomerResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO dto) {

        return customerService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        customerService.delete(id);
    }
}

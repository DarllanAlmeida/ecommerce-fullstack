package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.AddressRequestDTO;
import com.ecommerce.backend.dto.AddressResponseDTO;
import com.ecommerce.backend.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<AddressResponseDTO> getAll() {
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public AddressResponseDTO getById(@PathVariable Long id) {
        return addressService.getById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<AddressResponseDTO> getByCustomer(@PathVariable Long customerId) {
        return addressService.getByCustomer(customerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponseDTO create(@Valid @RequestBody AddressRequestDTO dto) {
        return addressService.create(dto);
    }

    @PutMapping("/{id}")
    public AddressResponseDTO update(@PathVariable Long id,
                                     @Valid @RequestBody AddressRequestDTO dto) {
        return addressService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        addressService.delete(id);
    }
}

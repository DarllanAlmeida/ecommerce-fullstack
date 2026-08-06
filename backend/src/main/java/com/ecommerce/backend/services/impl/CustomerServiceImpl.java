package com.ecommerce.backend.services.impl;

import com.ecommerce.backend.dto.CustomerRequestDTO;
import com.ecommerce.backend.dto.CustomerResponseDTO;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.mapper.CustomerMapper;
import com.ecommerce.backend.model.Customer;
import com.ecommerce.backend.model.Role;
import com.ecommerce.backend.repository.CustomerRepository;
import com.ecommerce.backend.repository.RoleRepository;
import com.ecommerce.backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getAll() {

        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO getById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado"));

        return CustomerMapper.toDTO(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO getByEmail(String email) {

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado"));

        return CustomerMapper.toDTO(customer);
    }

    @Override
    public CustomerResponseDTO create(CustomerRequestDTO dto) {

        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rol no encontrado"));

        Customer customer = CustomerMapper.toEntity(dto, role);

        Customer saved = customerRepository.save(customer);

        return CustomerMapper.toDTO(saved);
    }

    @Override
    public CustomerResponseDTO update(Long id, CustomerRequestDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado"));

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rol no encontrado"));

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword());
        customer.setPhone(dto.getPhone());
        customer.setRole(role);

        Customer updated = customerRepository.save(customer);

        return CustomerMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado"));

        customer.setActive(false);

        customerRepository.save(customer);
    }
}
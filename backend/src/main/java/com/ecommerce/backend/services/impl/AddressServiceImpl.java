package com.ecommerce.backend.services.impl;

import com.ecommerce.backend.dto.AddressRequestDTO;
import com.ecommerce.backend.dto.AddressResponseDTO;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.mapper.AddressMapper;
import com.ecommerce.backend.model.Address;
import com.ecommerce.backend.model.Customer;
import com.ecommerce.backend.repository.AddressRepository;
import com.ecommerce.backend.repository.CustomerRepository;
import com.ecommerce.backend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponseDTO> getAll() {

        return addressRepository.findAll()
                .stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AddressResponseDTO getById(Long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Dirección no encontrada"));

        return AddressMapper.toDTO(address);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponseDTO> getByCustomer(Long customerId) {

        return addressRepository.findByCustomerIdAndActiveTrue(customerId)
                .stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    @Override
    public AddressResponseDTO create(AddressRequestDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado"));

        // Solo puede existir una dirección predeterminada
        if (Boolean.TRUE.equals(dto.getIsDefault())) {

            List<Address> addresses =
                    addressRepository.findByCustomerId(customer.getId());

            for (Address item : addresses) {
                item.setIsDefault(false);
            }

            addressRepository.saveAll(addresses);
        }

        Address address = AddressMapper.toEntity(dto, customer);

        Address saved = addressRepository.save(address);

        return AddressMapper.toDTO(saved);
    }

    @Override
    public AddressResponseDTO update(Long id, AddressRequestDTO dto) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Dirección no encontrada"));

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado"));

        // Si la dirección pasa a ser la predeterminada,
        // las demás dejan de serlo.
        if (Boolean.TRUE.equals(dto.getIsDefault())) {

            List<Address> addresses =
                    addressRepository.findByCustomerId(customer.getId());

            for (Address item : addresses) {

                if (!item.getId().equals(address.getId())) {
                    item.setIsDefault(false);
                }
            }

            addressRepository.saveAll(addresses);
        }

        address.setStreet(dto.getStreet());
        address.setAddressLine2(dto.getAddressLine2());
        address.setCity(dto.getCity());
        address.setProvince(dto.getProvince());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());
        address.setReference(dto.getReference());
        address.setIsDefault(dto.getIsDefault());
        address.setCustomer(customer);

        Address updated = addressRepository.save(address);

        return AddressMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Dirección no encontrada"));

        address.setActive(false);

        addressRepository.save(address);
    }
}
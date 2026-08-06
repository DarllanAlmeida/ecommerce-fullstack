package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.AddressRequestDTO;
import com.ecommerce.backend.dto.AddressResponseDTO;
import com.ecommerce.backend.model.Address;
import com.ecommerce.backend.model.Customer;

public class AddressMapper {

    private AddressMapper() {
    }

    public static Address toEntity(AddressRequestDTO dto, Customer customer) {

        return Address.builder()
                .street(dto.getStreet())
                .addressLine2(dto.getAddressLine2())
                .city(dto.getCity())
                .province(dto.getProvince())
                .postalCode(dto.getPostalCode())
                .country(dto.getCountry())
                .reference(dto.getReference())
                .isDefault(dto.getIsDefault())
                .customer(customer)
                .active(true)
                .build();
    }

    public static AddressResponseDTO toDTO(Address address) {

        return AddressResponseDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .province(address.getProvince())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .reference(address.getReference())
                .isDefault(address.getIsDefault())
                .active(address.getActive())
                .customerId(address.getCustomer().getId())
                .customerName(
                        address.getCustomer().getFirstName() + " " +
                                address.getCustomer().getLastName()
                )
                .build();
    }

}

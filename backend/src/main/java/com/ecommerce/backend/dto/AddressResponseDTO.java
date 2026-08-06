package com.ecommerce.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponseDTO {

    private Long id;

    private String street;

    private String addressLine2;

    private String city;

    private String province;

    private String postalCode;

    private String country;

    private String reference;

    private Boolean isDefault;

    private Boolean active;

    private Long customerId;

    private String customerName;

}

package com.ecommerce.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandResponseDTO {

    private Long id;

    private String name;

    private String logoUrl;

    private String country;

    private String description;

    private Boolean active;

}

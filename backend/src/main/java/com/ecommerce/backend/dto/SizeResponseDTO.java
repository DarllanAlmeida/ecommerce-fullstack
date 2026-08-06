package com.ecommerce.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SizeResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Boolean active;

}

package com.example.e_commerce.DTO.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long id;

    private String productName;

    private String productDescription;

    private Double price;

    private int stock;

    private String brand;

}

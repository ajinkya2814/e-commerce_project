package com.example.e_commerce.DTO.Product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank
    private String productName;
    @NotBlank
    private String productDescription;
    @NotNull
    @Positive
    private Double price;
    @PositiveOrZero
    private int stock;
    @NotBlank
    private String brand;
}

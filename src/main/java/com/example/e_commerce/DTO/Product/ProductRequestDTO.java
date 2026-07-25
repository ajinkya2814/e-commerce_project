package com.example.e_commerce.DTO.Product;

import jakarta.validation.constraints.*;
import lombok.*;

@Data // Does Not have need of To string methods etc...
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "Product Name Required")
    @Size(min = 2, max = 50,
            message = "Product name should be between 2 to 50 Char")
    private String productName;
    @NotBlank(message = "Product Description Required")
    @Size(min = 2, max = 150,
            message = "Product Description should be between 2 to 150 Char")
    private String productDescription;
    @NotNull(message = "Price Required")
    @Positive(message = "price Should be Positive")
    private Double price;
    @PositiveOrZero(message = "Stock should be Positive or Zero ")
    private int stock;
    @NotBlank(message = "Brand Name required")
    @Size(min = 2, max = 50,
            message = "Brand name should be between 2 to 50 Char")
    private String brand;
}

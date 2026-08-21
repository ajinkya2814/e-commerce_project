package com.example.e_commerce.DTO.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.patterns.ScopeWithTypeVariables;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {

    @NotBlank(message = "Category name required")
    @Size(min = 4, max = 15, message = "Category name should be 4 to 15 characters")
    private String name;
    @Size(max = 500, message = "Description should be maximum 500 character long" )
    private String description;
}

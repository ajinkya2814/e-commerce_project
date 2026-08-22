package com.example.e_commerce.DTO.Address;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {

    @NotBlank(message = "AddressLine1 Required")
    @Size(min = 2, max = 150,
            message = "Address should be between 2 to 50 Char")
    private String addressLine1;
    @Size(min = 2, max = 50,
            message = "Address should be between 2 to 50 Char")
    private String addressLine2;
    @NotBlank(message = "City Required")
    @Size(min = 2, max = 50,
            message = "City should be between 2 to 50 Char")
    private String city;
    @NotBlank(message = "State Required")
    @Size(min = 2, max = 50,
            message = "State should be between 2 to 50 Char")
    private String state;
    @NotBlank(message = "Country Required")
    @Size(min = 2, max = 50,
            message = "Country should be between 2 to 50 Char")
    private String country;
    @NotBlank(message = "Zipcode Required")
    @Size(min = 2, max = 30,
            message = "Zipcode should be between 2 to 30 Char")
    private String zipCode;
    @NotBlank(message = "AddressType Required")
    @Size(min = 2, max = 50,
            message = "AddressType should be between 2 to 50 Char")
    private String addressType;
    private Boolean isDefault;
}

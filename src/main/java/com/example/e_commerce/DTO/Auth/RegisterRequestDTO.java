package com.example.e_commerce.DTO.Auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50,
    message = "first name should be between 2 to 50 Char")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50,
            message = "first name should be between 2 to 50 Characters")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Please Enter Valid Email Address" )
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;
    @NotBlank(message = "Password is Required")
    @Size(min = 8, max = 20, message = "password should be at least 20 Character")
    @Pattern(regexp =  "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$",
    message = "Password Should contain At least one LowerCase one UpperCase One special character and One Number ")
    private String password;
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must Contain exactly 10 digits"
    )
    private String phone;


}

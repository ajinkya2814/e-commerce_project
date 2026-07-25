package com.example.e_commerce.DTO.Auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Please Enter Valid Email Address" )
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;
    @NotBlank(message = "Password is Required")
    @Size(min = 8, max = 20, message = "password should be at least 20 Character")
    @Pattern(regexp =  "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$",
            message = "Password Should contain At least one LowerCase one UpperCase One special character and One Number ")
    private String password;

}

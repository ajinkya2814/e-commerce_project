package com.example.e_commerce.DTO.Auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {

//    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String message;
    private String token;
}

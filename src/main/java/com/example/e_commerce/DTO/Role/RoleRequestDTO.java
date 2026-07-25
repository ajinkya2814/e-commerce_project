package com.example.e_commerce.DTO.Role;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequestDTO {

    @NotBlank(message = "Role name is required")
    private String name;


}

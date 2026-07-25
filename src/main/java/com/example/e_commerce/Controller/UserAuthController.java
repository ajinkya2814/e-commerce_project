package com.example.e_commerce.Controller;

import com.example.e_commerce.DTO.Auth.AuthResponseDTO;
import com.example.e_commerce.DTO.Auth.LoginRequestDTO;
import com.example.e_commerce.DTO.Auth.RegisterRequestDTO;
import com.example.e_commerce.Service.UsersServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserAuthController {

    private final UsersServiceImplementation userService;


    @PostMapping("/userRegister")
    public ResponseEntity<AuthResponseDTO> createCustomer(@Valid @RequestBody RegisterRequestDTO userRegisterDTO)
    {
        AuthResponseDTO userRegisterResponseDTO = userService.userRegistration(userRegisterDTO);
        return  new ResponseEntity<>(userRegisterResponseDTO, HttpStatus.CREATED);
    }


    @PostMapping("/userLogin")
    public ResponseEntity<AuthResponseDTO> loginCustomer(@Valid @RequestBody LoginRequestDTO userLoginRequestDTO)
    {
        AuthResponseDTO userLoginResposeDTO = userService.UserLogin(userLoginRequestDTO);
        return  ResponseEntity.ok(userLoginResposeDTO);
    }

}

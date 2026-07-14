package com.example.e_commerce.Service;

import com.example.e_commerce.Constants.AppConstants;
import com.example.e_commerce.Constants.ErrorMessage;
import com.example.e_commerce.DTO.Auth.*;
import com.example.e_commerce.Exception.ResourceAlreadyExistException;
import com.example.e_commerce.Exception.ResourceNotFoundException;
import com.example.e_commerce.Model.Role;
import com.example.e_commerce.Model.Users;
import com.example.e_commerce.Repository.RoleRepository;
import com.example.e_commerce.Repository.UserRepository;
import com.example.e_commerce.Role.roleName;
import com.example.e_commerce.Security.JwtService;
import com.example.e_commerce.Security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersServiceImplementation {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public AuthResponseDTO userRegistration(RegisterRequestDTO requestDTO){
        // Normalization Using Helper Methods
        String firstname = (normalizeText(requestDTO.getFirstName()));
        String LastName =(normalizeText(requestDTO.getLastName()));
        String Email = (normalizeEmail(requestDTO.getEmail()));
        String Phone = (normalizeText(requestDTO.getPhone()));

        // Exist By email check---
        if (userRepository.existsByEmail(Email)){
            throw new ResourceAlreadyExistException(ErrorMessage.EMAIL_ALREADY_EXIST);
        }

        //Used to Set Default Role whenEver a User is created.
        Role customerRole =  roleRepository.findByName(roleName.CUSTOMER.name()).orElseThrow(()->new ResourceNotFoundException("Invalid Role"));

        // this is used for Password Security
        String encodePass = (bCryptPasswordEncoder.encode(requestDTO.getPassword().trim()));

        Users users = Users.builder()
                .firstName(firstname)
                .lastName(LastName)
                .email(Email)
                .phone(Phone)
                .password(encodePass)
                .role(customerRole)
                .build();

        Users saveUsers = userRepository.save(users);

        // email service
        emailService.sendWelcomeEmail(saveUsers.getEmail(),saveUsers.getFirstName(),saveUsers.getLastName());

        return buildAuthResponse(saveUsers,AppConstants.REGISTER_SUCCESS);
    }


    @Transactional (readOnly = true)
    public AuthResponseDTO UserLogin(LoginRequestDTO requestDTO){

        String email = normalizeEmail(requestDTO.getEmail());
        String password = normalizeText(requestDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        Users users = principal.getUsers();



//        Users users = userRepository.findByEmail(email)
//                .orElseThrow(() ->
//                        new UnAuthorizedException(ErrorMessage.INVALID_EMAIL_OR_PASSWORD));
//
//        if (!users.getIsActive()) {
//            throw new UnAuthorizedException(ErrorMessage.ACCOUNT_DISABLE);
//        }
//        if (!users.getEmailVerified()) {
//            throw new UnAuthorizedException(ErrorMessage.EMAIL_NOT_VERIFIED);
//        }
//        if (!users.getAccountNonLocked()) {
//            throw new UnAuthorizedException(ErrorMessage.ACCOUNT_LOCKED);
//        }
//        if (!bCryptPasswordEncoder.matches(password, users.getPassword())) {
//            throw new UnAuthorizedException(ErrorMessage.INVALID_EMAIL_OR_PASSWORD);
//        }
        // Using Builder----
//        return AuthResponseDTO.builder()
//                .id(users.getId())
//                .firstName(users.getFirstName())
//                .lastName(users.getLastName())
//                .email(users.getEmail())
//                .message(AppConstants.LOGIN_SUCCESS)
//                .role(users.getRole().getName())
//                .build();
        return buildAuthResponse(users, AppConstants.LOGIN_SUCCESS);

    }

    //helper Method----
    private String normalizeEmail(String email){
        return email.trim().toLowerCase();
    }
    private String normalizeText(String value){
        return value.trim();
    }

    //helper Method---
    private AuthResponseDTO buildAuthResponse(Users users, String message) {

        AuthResponseDTO response =
                modelMapper.map(users, AuthResponseDTO.class);

        response.setRole(users.getRole().getName());
        response.setMessage(message);

        // Generate JWT so the client gets a usable token right after register/login
        String token = jwtService.generateToken(new UserPrincipal(users));
        response.setToken(token);
        return response;
    }
}

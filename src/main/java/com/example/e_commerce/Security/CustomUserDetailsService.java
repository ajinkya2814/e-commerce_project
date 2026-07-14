package com.example.e_commerce.Security;

import com.example.e_commerce.Constants.ErrorMessage;
import com.example.e_commerce.Model.Users;
import com.example.e_commerce.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.INVALID_EMAIL_OR_PASSWORD));
        return new UserPrincipal(users);
    }
}

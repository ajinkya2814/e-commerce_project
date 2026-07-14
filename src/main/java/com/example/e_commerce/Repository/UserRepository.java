package com.example.e_commerce.Repository;

import com.example.e_commerce.Model.Role;
import com.example.e_commerce.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <Users, Long> {

    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String email);

}

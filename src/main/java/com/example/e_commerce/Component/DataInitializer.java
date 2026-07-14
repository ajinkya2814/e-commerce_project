package com.example.e_commerce.Component;

import com.example.e_commerce.Model.Role;
import com.example.e_commerce.Repository.RoleRepository;
import com.example.e_commerce.Role.roleName;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRoleIfNotExists(roleName.ADMIN.name(), "System Administrator");
        createRoleIfNotExists(roleName.SELLER.name(), "Product Seller");
        createRoleIfNotExists(roleName.CUSTOMER.name(), "Customer");
    }

    private void createRoleIfNotExists(String name, String description) {
        if (roleRepository.findByName(name).isEmpty()) {
            Role role = Role.builder()
                    .name(name)
                    .build();

            roleRepository.save(role);
            System.out.println(name + " role created successfully");
        } else {
            System.out.println(name + " role already exists");
        }
    }
}
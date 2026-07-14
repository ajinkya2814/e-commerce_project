package com.example.e_commerce.Controller;

import com.example.e_commerce.DTO.Product.ProductRequestDTO;
import com.example.e_commerce.DTO.Product.ProductResponseDTO;
import com.example.e_commerce.DTO.Role.RoleRequestDTO;
import com.example.e_commerce.DTO.Role.RoleResponseDTO;
import com.example.e_commerce.Service.RoleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Validated
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping("/uplode")
    public ResponseEntity<RoleResponseDTO> save (@Valid @RequestBody RoleRequestDTO requestDTO){
        return ResponseEntity.ok(roleService.createRole(requestDTO));
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getById (@Valid @PathVariable Long id){
        return ResponseEntity.ok(roleService.getRole(id));
    }

    @GetMapping("/viewall")
    public ResponseEntity<List<RoleResponseDTO>> findAll(){
        return ResponseEntity.ok(roleService.getAll());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<RoleResponseDTO> update1(@Valid @PathVariable Long id, @RequestBody RoleRequestDTO requestDTO){
        return ResponseEntity.ok(roleService.update(id,requestDTO));
    }
}

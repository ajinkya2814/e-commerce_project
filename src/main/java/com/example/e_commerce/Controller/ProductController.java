package com.example.e_commerce.Controller;


import com.example.e_commerce.DTO.Product.ProductRequestDTO;
import com.example.e_commerce.DTO.Product.ProductResponseDTO;
import com.example.e_commerce.Service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {


    @Autowired
    private ProductServiceImpl productService;


    @PostMapping("/uplode")
    public ResponseEntity<ProductResponseDTO> save (@Valid @RequestBody ProductRequestDTO requestDTO){
        return ResponseEntity.ok(productService.save1(requestDTO));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById (@Valid @PathVariable Long id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/viewall")
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductResponseDTO> update1(@Valid @PathVariable Long id, @RequestBody ProductRequestDTO requestDTO){
        return ResponseEntity.ok(productService.update(id,requestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(productService.delete(id));
    }
}

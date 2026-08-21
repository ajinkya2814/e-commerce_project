package com.example.e_commerce.Controller;

import com.example.e_commerce.DTO.Category.CategoryRequestDTO;
import com.example.e_commerce.DTO.Category.CategoryResponseDTO;
import com.example.e_commerce.Model.Category;
import com.example.e_commerce.Service.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Validated
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping("/uplode")
    public ResponseEntity<CategoryResponseDTO> save(@Valid @RequestBody CategoryRequestDTO requestDTO){
        return ResponseEntity.ok(categoryService.save(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getId(@Valid @PathVariable Long id){
        return ResponseEntity.ok(categoryService.getByID(id));
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<CategoryResponseDTO>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@Valid @PathVariable Long id, @RequestBody CategoryRequestDTO requestDTO){
        return ResponseEntity.ok(categoryService.update(id,requestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id){
        return ResponseEntity.ok(categoryService.delete(id));
    }
}

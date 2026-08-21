package com.example.e_commerce.Service;

import com.example.e_commerce.DTO.Category.CategoryRequestDTO;
import com.example.e_commerce.DTO.Category.CategoryResponseDTO;
import com.example.e_commerce.Exception.ResourceNotFoundException;
import com.example.e_commerce.Model.Category;
import com.example.e_commerce.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryResponseDTO save(CategoryRequestDTO requestDTO){
        Category category = modelMapper.map(requestDTO, Category.class);

        Category savecategory = categoryRepository.save(category);

        return modelMapper.map(savecategory, CategoryResponseDTO.class);
    }

    public CategoryResponseDTO getByID(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No category found with id : " + id));

        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
    public List<CategoryResponseDTO> getAll(){
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build())
                .toList();
    }

    public CategoryResponseDTO update(Long id, CategoryRequestDTO requestDTO){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No category found with id : " + id));

        modelMapper.map(requestDTO, category);
        Category saveCategory = categoryRepository.save(category);

        return CategoryResponseDTO.builder()
                .id(saveCategory.getId())
                .name(saveCategory.getName())
                .description(saveCategory.getDescription())
                .build();
    }

    public String delete(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No category found with id :" + id));

        categoryRepository.delete(category);
        return "Category Deleted Successfully....";
    }
}

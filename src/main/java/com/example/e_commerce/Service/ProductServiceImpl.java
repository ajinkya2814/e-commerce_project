package com.example.e_commerce.Service;


import com.example.e_commerce.DTO.Product.ProductRequestDTO;
import com.example.e_commerce.DTO.Product.ProductResponseDTO;
import com.example.e_commerce.Exception.ResourceNotFoundException;
import com.example.e_commerce.Model.Product;
import com.example.e_commerce.Repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ProductResponseDTO save1(ProductRequestDTO requestDTO){
        Product product = modelMapper.map(requestDTO, Product.class);

        // Role customerRole = roleRepository.findByName("CUSTOMER").orElseThrow(() -> new RoleNotFoundException("Role Not Found..."));
        Product saveproduct= productRepository.save(product);

        return modelMapper.map(saveproduct, ProductResponseDTO.class);

    }

    public ProductResponseDTO getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No product found with id : " + id));

        return ProductResponseDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .brand(product.getBrand())
                .build();
    }


    public List<ProductResponseDTO> getAll() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> ProductResponseDTO.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .productDescription(product.getProductDescription())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .brand(product.getBrand())
                        .build())
                .toList();
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        modelMapper.map(dto, product);

        Product updatedProduct = productRepository.save(product);

        return ProductResponseDTO.builder()
                .id(updatedProduct.getId())
                .productName(updatedProduct.getProductName())
                .productDescription(updatedProduct.getProductDescription())
                .price(updatedProduct.getPrice())
                .stock(updatedProduct.getStock())
                .brand(updatedProduct.getBrand())
                .build();
    }

    public String delete(Long id){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mobile Not Found"));

        productRepository.delete(product);

        return "Product Deleted Successfully....";
    }
}

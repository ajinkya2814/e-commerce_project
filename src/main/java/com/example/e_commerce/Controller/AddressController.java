package com.example.e_commerce.Controller;

import com.example.e_commerce.DTO.Address.AddressRequestDTO;
import com.example.e_commerce.DTO.Address.AddressResponseDTO;
import com.example.e_commerce.Service.AddressServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressServiceImpl addressService;

    @PostMapping("/uplode")
    public ResponseEntity<AddressResponseDTO> save(@Valid @RequestBody AddressRequestDTO requestDTO){
        return ResponseEntity.ok(addressService.saveAddress(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getId(@Valid @PathVariable Long id){
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<AddressResponseDTO>> getAll(){
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AddressResponseDTO> update(@Valid @PathVariable Long id, @RequestBody AddressRequestDTO requestDTO){
        return ResponseEntity.ok(addressService.updateAddress(id,requestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id){
        return ResponseEntity.ok(addressService.deleteAddress(id));
    }
}

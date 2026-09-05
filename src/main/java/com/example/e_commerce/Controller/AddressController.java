package com.example.e_commerce.Controller;

import com.example.e_commerce.DTO.Address.AddressRequestDTO;
import com.example.e_commerce.DTO.Address.AddressResponseDTO;
import com.example.e_commerce.Security.UserPrincipal;
import com.example.e_commerce.Service.AddressServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressServiceImpl addressService;

    @PostMapping("/uplode")
    public ResponseEntity<AddressResponseDTO> save(
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody AddressRequestDTO requestDTO) {
        return ResponseEntity.ok(addressService.saveAddress(principal.getUsers(), requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getId(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressById(principal.getUsers(), id));
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<AddressResponseDTO>> getAll(
            @AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(addressService.getAllAddress(principal.getUsers()));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AddressResponseDTO> update(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long id,
            @Valid @RequestBody AddressRequestDTO requestDTO) {
        return ResponseEntity.ok(addressService.updateAddress(principal.getUsers(), id, requestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long id) {
        return ResponseEntity.ok(addressService.deleteAddress(principal.getUsers(), id));
    }
}
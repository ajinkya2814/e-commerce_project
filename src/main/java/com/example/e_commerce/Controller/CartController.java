package com.example.e_commerce.Controller;

import com.example.e_commerce.DTO.Cart_CartItem.CartItemRequestDTO;
import com.example.e_commerce.DTO.Cart_CartItem.CartResponseDTO;
import com.example.e_commerce.Security.UserPrincipal;
import com.example.e_commerce.Service.CartServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
@Validated
public class CartController {

    private final CartServiceImpl cartService;

    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addItem(
//            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody CartItemRequestDTO requestDTO) {
        // TEMPORARY FIX: Hardcode user ID 1 for testing purposes
        Long userId = 1L;

        // Once Security is fully working, you will switch it back to:
        // Long userId = principal.getUsers().getId();

        return ResponseEntity.ok(cartService.addItemsToCart(userId, requestDTO));
//        return ResponseEntity.ok(cartService.addItemsToCart(principal.getUsers(), requestDTO));
    }

    @GetMapping
    public ResponseEntity<CartResponseDTO> viewCart(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(cartService.viewCart(principal.getUsers()));
    }

    @PutMapping("/item/{itemId}")
    public ResponseEntity<CartResponseDTO> updateItem(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long itemId,
            @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateItemQuantity(principal.getUsers(), itemId, quantity));
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<CartResponseDTO> removeItem(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long itemId) {
        return ResponseEntity.ok(cartService.removeItem(principal.getUsers(), itemId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(cartService.clearCart(principal.getUsers()));
    }
}

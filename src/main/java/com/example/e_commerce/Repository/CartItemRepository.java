package com.example.e_commerce.Repository;

import com.example.e_commerce.Model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {
    Optional<CartItems> findByCartIdAndProductId(Long cartId, Long productId);
    Optional<CartItems> findByIdAndCartId(Long itemId, Long cartId);
}

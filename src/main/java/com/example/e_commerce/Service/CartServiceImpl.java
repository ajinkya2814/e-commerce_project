package com.example.e_commerce.Service;

import com.example.e_commerce.Constants.ErrorMessage;
import com.example.e_commerce.DTO.Cart_CartItem.CartItemRequestDTO;
import com.example.e_commerce.DTO.Cart_CartItem.CartItemResponseDTO;
import com.example.e_commerce.DTO.Cart_CartItem.CartResponseDTO;
import com.example.e_commerce.Exception.BadRequestException;
import com.example.e_commerce.Exception.ResourceNotFoundException;
import com.example.e_commerce.Model.Cart;
import com.example.e_commerce.Model.CartItems;
import com.example.e_commerce.Model.Product;
import com.example.e_commerce.Model.Users;
import com.example.e_commerce.Repository.CartItemRepository;
import com.example.e_commerce.Repository.CartRepository;
import com.example.e_commerce.Repository.ProductRepository;
import com.example.e_commerce.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // Fetch existing cart for user, or create a new empty one if none exists yet
    private Cart getOrCreateCart (Users user){
        return cartRepository.findByUserId(user.getId())
                .orElseGet(()-> cartRepository.save(
                        Cart.builder()
                                .user(user)
                                .build()
                ));
    }

    @Transactional
    public CartResponseDTO addItemsToCart (Long id, CartItemRequestDTO requestDTO){
        // 1. Fetch the user entity using the ID
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));

        Cart cart = getOrCreateCart(user);

        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("No product found with id : " + requestDTO.getProductId()));

        CartItems item = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId())
                .orElse(null);

        int newQuantity = (item != null ? item.getQuantity() : 0) + requestDTO.getQuantity();

        if (newQuantity > product.getStock()) {
            throw new BadRequestException(ErrorMessage.INSUFFICIENT_STOCK);
        }

        if (item != null) {
            item.setQuantity(newQuantity);
            cartItemRepository.save(item);
        } else {
            CartItems newItem = CartItems.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(requestDTO.getQuantity())
                    .build();
            cartItemRepository.save(newItem);
        }

        return viewCart(user);
    }

    @Transactional
    public CartResponseDTO updateItemQuantity(Users user, Long id, int quantity) {

        if (quantity < 1) {
            throw new BadRequestException("Quantity must be at least 1");
        }

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.CART_NOT_FOUND));

        CartItems item = cartItemRepository.findByIdAndCartId(id, cart.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.CART_ITEM_NOT_FOUND));

        if (quantity > item.getProduct().getStock()) {
            throw new BadRequestException(ErrorMessage.INSUFFICIENT_STOCK);
        }

        item.setQuantity(quantity);
        cartItemRepository.save(item);

        return viewCart(user);
    }

    @Transactional
    public CartResponseDTO removeItem(Users user, Long id) {

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.CART_NOT_FOUND));

        CartItems item = cartItemRepository.findByIdAndCartId(id, cart.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.CART_ITEM_NOT_FOUND));

        cart.getItems().remove(item);
        cartItemRepository.delete(item);

        return viewCart(user);
    }

    @Transactional
    public String clearCart(Users user) {

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.CART_NOT_FOUND));

        cart.getItems().clear();
        cartRepository.save(cart);

        return "Cart Cleared Successfully....";
    }

    public CartResponseDTO viewCart(Users user) {

        Cart cart = getOrCreateCart(user);

        List<CartItemResponseDTO> itemDTOs = cart.getItems().stream()
                .map(item -> {
                    double subtotal = item.getProduct().getPrice() * item.getQuantity();
                    return CartItemResponseDTO.builder()
                            .id(item.getId())
                            .productId(item.getProduct().getId())
                            .productName(item.getProduct().getProductName())
                            .unitPrice(item.getProduct().getPrice())
                            .quantity(item.getQuantity())
                            .subtotal(subtotal)
                            .build();
                })
                .toList();

        double total = itemDTOs.stream().mapToDouble(CartItemResponseDTO::getSubtotal).sum();
        int totalItems = itemDTOs.stream().mapToInt(CartItemResponseDTO::getQuantity).sum();

        return CartResponseDTO.builder()
                .id(cart.getId())
                .items(itemDTOs)
                .totalPrice(total)
                .totalItems(totalItems)
                .build();
    }
}

package com.example.e_commerce.DTO.Cart_CartItem;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDTO {

    private Long id;
    private List<CartItemResponseDTO> items;
    private Double totalPrice;
    private int totalItems;


}

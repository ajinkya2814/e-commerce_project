package com.example.e_commerce.Model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product extends BaseClassEntity{

    @Column(nullable = false,length = 100)
    private String productName;
    @Column(nullable = false,length = 300)
    private String productDescription;
    @Column(nullable = false)
    private Double price;
    private int stock;
    private String brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Users seller;

}

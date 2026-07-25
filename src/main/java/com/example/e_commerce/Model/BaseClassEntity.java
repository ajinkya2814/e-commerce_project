package com.example.e_commerce.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist(){
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        updateAt = LocalDateTime.now();
    }
}

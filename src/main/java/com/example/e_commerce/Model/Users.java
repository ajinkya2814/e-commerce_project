package com.example.e_commerce.Model;

import jakarta.persistence.*;
import lombok.*;

@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_email", columnNames = "email")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Users extends BaseClassEntity{

    @Column(nullable = false,name = "first_name",length = 50)
    private String firstName;
    @Column(nullable = false,name = "last_name",length = 50)
    private String lastName;
    @Column(nullable = false,unique = true,length = 100)
    private String email;
    @Column(nullable = false,length = 255)
    private String password;
    @Column(nullable = false, length = 15)
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "role_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_role"))
    private Role role;

    @Column(name = "is_active",
            nullable = false)
    @Builder.Default
    private Boolean isActive = true;
    @Column(name = "is_email_verified",
            nullable = false)
    @Builder.Default
    private Boolean emailVerified = false;
    @Column(name = "is_active_notLocked",
            nullable = false)
    @Builder.Default
    private Boolean accountNonLocked = true;
    @Column(nullable = false)
    @Builder.Default
    private Boolean accountNonExpired = true;
    @Column(nullable = false)
    @Builder.Default
    private Boolean credentialsNonExpired = true;



}

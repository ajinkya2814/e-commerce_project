package com.example.e_commerce.Model;


import aj.org.objectweb.asm.commons.Remapper;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
            }
        )
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseClassEntity{

    @Column(unique = true,nullable = false)
    private String name;


}

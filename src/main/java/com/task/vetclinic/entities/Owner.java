package com.task.vetclinic.entities;

import com.task.vetclinic.enums.Gender;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Owner extends BaseEntity{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Pet> pets;
}

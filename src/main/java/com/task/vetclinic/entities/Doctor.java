package com.task.vetclinic.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Doctor extends BaseEntity{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    @Lob
    private byte[] photo;
    private String bio;
    @ManyToOne
    @JoinColumn(name = "clinic_id",referencedColumnName = "id")
    private Clinic clinic;
}

package com.task.vetclinic.entities;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Clinic extends BaseEntity{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String phone;
    private Double startWorkingHour;
    private Double endWorkingHour;
    private String email;
    private String address;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<DayOfWeek> workingDays;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<String> socialNetworksURLs;
    @OneToMany(mappedBy = "clinic",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Doctor> doctors;
}

package com.task.vetclinic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task.vetclinic.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PetDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Gender gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String animalKind;
    private Long weight;
    private OwnerDto owner;
}

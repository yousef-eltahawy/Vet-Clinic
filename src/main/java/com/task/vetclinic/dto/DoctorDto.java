package com.task.vetclinic.dto;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String phone;
    private String bio;
}

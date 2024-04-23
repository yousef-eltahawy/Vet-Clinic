package com.task.vetclinic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VisitResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private PetDto pet;
    private DoctorDto doctor;
    private ClinicDto clinic;


}

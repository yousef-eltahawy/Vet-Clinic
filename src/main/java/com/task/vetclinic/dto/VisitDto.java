package com.task.vetclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VisitDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDateTime date;
    private Long petId;
    private Long doctorId;
    private Long clinicId;
}

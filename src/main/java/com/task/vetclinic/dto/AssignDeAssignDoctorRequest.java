package com.task.vetclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AssignDeAssignDoctorRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long clinicId;
    private Long doctorId;
}

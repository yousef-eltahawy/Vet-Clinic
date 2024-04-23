package com.task.vetclinic.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClinicDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String phone;
    private Double startWorkingHour;
    private Double endWorkingHour;
    private String email;
    private String address;
    private List<DayOfWeek> workingDays;
    private List<String> socialNetworksURLs;
}

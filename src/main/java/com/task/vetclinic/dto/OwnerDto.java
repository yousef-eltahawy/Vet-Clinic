package com.task.vetclinic.dto;

import com.task.vetclinic.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class OwnerDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Gender gender;
    private String address;

}

package com.task.vetclinic.Services.interfaces;

import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.AssignDeAssignDoctorRequest;
import com.task.vetclinic.dto.ClinicDto;

public interface ClinicService {
    SuccessResponse<?> createClinic(ClinicDto dto);

    SuccessResponse<?> getClinic(Long id);

    SuccessResponse<?> searchClinicByPhoneOrAddress(String keyword);

    SuccessResponse<?> getDoctorListByClinicId(Long id);

    SuccessResponse<?> assignDoctor(AssignDeAssignDoctorRequest dto);

    SuccessResponse<?> deAssignDoctor(AssignDeAssignDoctorRequest dto);
}

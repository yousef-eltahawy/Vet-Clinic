package com.task.vetclinic.Services.interfaces;

import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.DoctorDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DoctorService {
    SuccessResponse<?> createDoctor(DoctorDto dto);

    SuccessResponse<?> getDoctor(Long id);

    SuccessResponse<?> uploadImage(Long doctorId, MultipartFile file) throws IOException;

    byte[] downloadImage(Long doctorId);
}

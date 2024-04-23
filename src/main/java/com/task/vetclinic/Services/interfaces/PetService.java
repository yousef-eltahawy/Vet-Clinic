package com.task.vetclinic.Services.interfaces;

import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.PetDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PetService {

    SuccessResponse<?> createPet(Long ownerId, PetDto dto);

    SuccessResponse<?> getPet(Long id);

    SuccessResponse<?> uploadImage(Long petId, MultipartFile file) throws IOException;

    byte[] downloadImage(Long petId);
}

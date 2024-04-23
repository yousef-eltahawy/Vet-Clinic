package com.task.vetclinic.Services.interfaces;

import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.OwnerDto;

public interface OwnerService {
    SuccessResponse<?> createOwner(OwnerDto dto);

    SuccessResponse<?> getOwner(Long id);

    SuccessResponse<?> getPetListByOwnerId(Long id);
}

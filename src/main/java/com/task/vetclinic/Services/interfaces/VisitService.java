package com.task.vetclinic.Services.interfaces;

import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.VisitDto;

public interface VisitService {
    SuccessResponse<?> createVisit(VisitDto dto);

    SuccessResponse<?> getVisit(Long id);
}

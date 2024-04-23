package com.task.vetclinic.Services.impl;

import com.task.vetclinic.Repository.ClinicRepository;
import com.task.vetclinic.Repository.DoctorRepository;
import com.task.vetclinic.Repository.PetRepository;
import com.task.vetclinic.Repository.VisitRepository;
import com.task.vetclinic.Services.interfaces.VisitService;
import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.VisitDto;
import com.task.vetclinic.dto.VisitResponseDto;
import com.task.vetclinic.entities.Clinic;
import com.task.vetclinic.entities.Doctor;
import com.task.vetclinic.entities.Pet;
import com.task.vetclinic.entities.Visit;
import com.task.vetclinic.exception.CustomException;
import com.task.vetclinic.util.ResponseIntegerKeys;
import com.task.vetclinic.util.ResponseStringKeys;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VisitServiceImp implements VisitService {
    private final VisitRepository visitRepository;
    private final ModelMapper mapper;
    private final PetRepository petRepository;
    private final DoctorRepository doctorRepository;
    private final ClinicRepository clinicRepository;
    public VisitServiceImp( VisitRepository visitRepository,PetRepository petRepository,
                            DoctorRepository doctorRepository, ClinicRepository clinicRepository){

        this.visitRepository=visitRepository;
        this.petRepository=petRepository;
        this.doctorRepository=doctorRepository;
        this.clinicRepository=clinicRepository;
        this.mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

    }

    @Override
    public SuccessResponse<?> createVisit(VisitDto dto){

        Visit visit=validateVisit(dto);
        visit=visitRepository.save(visit);
        return new SuccessResponse<>(ResponseStringKeys.CREATED,ResponseIntegerKeys.CREATED
                ,mapper.map(visit,VisitResponseDto.class));
    }


    @Override
    public SuccessResponse<?> getVisit(Long id){

        return visitRepository.findById(id).map(v->new SuccessResponse<>(ResponseStringKeys.OK,
                ResponseIntegerKeys.OK,mapper.map(v, VisitResponseDto.class))).orElse(
                new SuccessResponse<>(ResponseStringKeys.NOT_FOUND,ResponseIntegerKeys.NOT_FOUND));
    }

    private Visit validateVisit(VisitDto dto){
        Optional<Clinic> optClinic = clinicRepository.findById(dto.getClinicId());
        if(optClinic.isEmpty())
            throw new CustomException(String.format("there is no clinic with id : %d",dto.getClinicId())
                    ,ResponseIntegerKeys.NOT_FOUND);

        Optional<Doctor> optDoctor =optClinic.get().getDoctors().stream()
                .filter(d->d.getId().equals(dto.getDoctorId())).findFirst();
        if(optDoctor.isEmpty())
            throw new CustomException(String.format("there isn't a doctor with id : %d for clinic with id : %d ",dto.getDoctorId(),dto.getClinicId())
                    ,ResponseIntegerKeys.NOT_FOUND);

        Optional<Pet> optPet = petRepository.findById(dto.getPetId());
        if(optPet.isEmpty())
            throw new CustomException(String.format("there is no Pet with id : %d",dto.getClinicId())
                    ,ResponseIntegerKeys.NOT_FOUND);

        dateIsValid(dto.getDate());

        return Visit.builder()
                .clinic(optClinic.get())
                .doctor(optDoctor.get())
                .pet(optPet.get())
                .date(dto.getDate())
                .build();
    }
    private void dateIsValid(LocalDateTime date){
        LocalDateTime current=LocalDateTime.now().withMinute(0).withSecond(0);
        if(date.isBefore(current))
            throw new CustomException("visit date can't be before current date",ResponseIntegerKeys.NOT_FOUND);
    }

}

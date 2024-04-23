package com.task.vetclinic.Services.impl;

import com.task.vetclinic.Repository.ClinicRepository;
import com.task.vetclinic.Repository.DoctorRepository;
import com.task.vetclinic.Services.interfaces.ClinicService;
import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.AssignDeAssignDoctorRequest;
import com.task.vetclinic.dto.ClinicDto;
import com.task.vetclinic.dto.DoctorDto;
import com.task.vetclinic.entities.Clinic;
import com.task.vetclinic.entities.Doctor;
import com.task.vetclinic.exception.CustomException;
import com.task.vetclinic.util.ResponseIntegerKeys;
import com.task.vetclinic.util.ResponseStringKeys;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceServiceImp implements ClinicService {
    private final ClinicRepository clinicRepository;
    private final DoctorRepository doctorRepository;

    private final ModelMapper mapper;


    public ClinicServiceServiceImp(ClinicRepository clinicRepository,DoctorRepository doctorRepository){
        this.clinicRepository=clinicRepository;
        this.doctorRepository=doctorRepository;
        this.mapper= new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

    }
    @Override
    public SuccessResponse<?> createClinic(ClinicDto dto){
        Optional<Clinic> optClinic=clinicRepository.findByName(dto.getName());
        if(optClinic.isPresent())
            throw new CustomException("this clinic already exists",ResponseIntegerKeys.DATA_INTEGRITY_VIOLATION);

        Clinic clinic=mapper.map(dto,Clinic.class);
        clinic =clinicRepository.save(clinic);

        return new SuccessResponse<>(ResponseStringKeys.CREATED, ResponseIntegerKeys.CREATED
                ,mapper.map(clinic, ClinicDto.class));
    }
    @Override
    public SuccessResponse<?> getClinic(Long id){

        Clinic clinic= validateClinic(id);

        return new SuccessResponse<ClinicDto>(ResponseStringKeys.OK
                ,ResponseIntegerKeys.OK, mapper.map(clinic, ClinicDto.class));
    }
    @Override
    public SuccessResponse<?> searchClinicByPhoneOrAddress(String keyword){
        List<Clinic> clinics=clinicRepository.searchByPhoneOrAddress(keyword);

        return new SuccessResponse<>(ResponseStringKeys.OK,ResponseIntegerKeys.OK,clinics);
    }
    @Override
    public SuccessResponse<?> getDoctorListByClinicId(Long id){

        Clinic clinic= validateClinic(id);

        List<Doctor> doctors=clinic.getDoctors();
        System.out.println(doctors.size());
        if(doctors.isEmpty())
            new SuccessResponse<>("there are no doctors for this clinic",ResponseIntegerKeys.NOT_FOUND);

        List<DoctorDto> doctorDtos = doctors.stream()
                .map(d->mapper.map(d,DoctorDto.class)).toList();

        return new SuccessResponse<>(ResponseStringKeys.OK,ResponseIntegerKeys.OK,doctorDtos);

    }
    @Override
    public SuccessResponse<?> assignDoctor(AssignDeAssignDoctorRequest dto){

        Clinic clinic= validateClinic(dto.getClinicId());

        Optional<Doctor> optDoctor=doctorRepository.findById(dto.getDoctorId());
        if(optDoctor.isEmpty())
            throw new CustomException("this doctor doesn't exists",ResponseIntegerKeys.NOT_FOUND);
        if(optDoctor.get().getClinic() != null)
            throw new CustomException("this doctor is assigned to another clinic", ResponseIntegerKeys.DATA_INTEGRITY_VIOLATION);

        clinic.getDoctors().add(optDoctor.get());
        optDoctor.get().setClinic(clinic);
        clinicRepository.save(clinic);

        return new SuccessResponse<>("Successfully Assigned", ResponseIntegerKeys.OK);
    }
    @Override
    public SuccessResponse<?> deAssignDoctor(AssignDeAssignDoctorRequest dto){

        Clinic clinic= validateClinic(dto.getClinicId());

        Optional<Doctor> optDoctor=clinic.getDoctors().stream()
                .filter(d->d.getId().equals(dto.getDoctorId())).findFirst();
        if(optDoctor.isPresent()){
            optDoctor.get().setClinic(null);
            clinicRepository.save(clinic);
        }
        else
            throw new CustomException(String.format("there isn't a doctor with id : %d for clinic with id : %d ",dto.getDoctorId(),dto.getClinicId())
                    ,ResponseIntegerKeys.NOT_FOUND);

        return new SuccessResponse<>("Successfully De-Assigned", ResponseIntegerKeys.OK);
    }

    private Clinic validateClinic(Long id){
        Optional<Clinic> optClinic=clinicRepository.findById(id);
        if(optClinic.isEmpty())
            throw new CustomException("this clinic doesn't exit",ResponseIntegerKeys.NOT_FOUND);
        return optClinic.get();
    }
}

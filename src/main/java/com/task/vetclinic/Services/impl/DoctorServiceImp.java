package com.task.vetclinic.Services.impl;

import com.task.vetclinic.Repository.DoctorRepository;
import com.task.vetclinic.Services.interfaces.DoctorService;
import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.DoctorDto;
import com.task.vetclinic.entities.Doctor;
import com.task.vetclinic.entities.Pet;
import com.task.vetclinic.exception.CustomException;
import com.task.vetclinic.util.ImageUtils;
import com.task.vetclinic.util.ResponseIntegerKeys;
import com.task.vetclinic.util.ResponseStringKeys;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper mapper;

    public DoctorServiceImp(DoctorRepository doctorRepository){
        this.doctorRepository=doctorRepository;
        this.mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

    }

    @Override
    public SuccessResponse<?> createDoctor(DoctorDto dto){
        Optional<Doctor>optDoctor=doctorRepository.findByName(dto.getName());
        if(optDoctor.isPresent())
            throw new CustomException("this doctor already exists", ResponseIntegerKeys.DATA_INTEGRITY_VIOLATION);
        Doctor doctor=mapper.map(dto,Doctor.class);
        doctor=doctorRepository.save(doctor);
        return new SuccessResponse<>(ResponseStringKeys.CREATED,ResponseIntegerKeys.CREATED
                ,mapper.map(doctor, DoctorDto.class));
    }
    @Override
    public SuccessResponse<?> getDoctor(Long id){

        return doctorRepository.findById(id).map(d->new SuccessResponse<>(ResponseStringKeys.OK,
                ResponseIntegerKeys.OK,mapper.map(d,DoctorDto.class))).orElse(
                        new SuccessResponse<>(ResponseStringKeys.NOT_FOUND,ResponseIntegerKeys.NOT_FOUND));
    }
    @Override
    public SuccessResponse<?> uploadImage(Long doctorId, MultipartFile file) throws IOException {
        Optional<Doctor> optDoctor=doctorRepository.findById(doctorId);
        if (optDoctor.isEmpty())
            throw new CustomException(ResponseStringKeys.NOT_FOUND,ResponseIntegerKeys.NOT_FOUND);
        optDoctor.get().setPhoto(ImageUtils.compressImage(file.getBytes()));
        doctorRepository.save(optDoctor.get());
        return new SuccessResponse<>(ResponseStringKeys.OK,ResponseIntegerKeys.OK);
    }
    @Override
    public byte[] downloadImage(Long doctorId) {
        Optional<Doctor> optDoctor=doctorRepository.findById(doctorId);
        if (optDoctor.isEmpty())
            throw new CustomException(ResponseStringKeys.NOT_FOUND,ResponseIntegerKeys.NOT_FOUND);

        try {
            return ImageUtils.decompressImage(optDoctor.get().getPhoto());
        } catch (DataFormatException | IOException exception) {
            throw new CustomException(String.format("Error downloading an image with id %d",doctorId),ResponseIntegerKeys.DATA_INTEGRITY_VIOLATION);
        }
    }
}

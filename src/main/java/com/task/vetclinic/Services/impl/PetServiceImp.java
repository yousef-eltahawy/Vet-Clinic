package com.task.vetclinic.Services.impl;

import com.task.vetclinic.Repository.OwnerRepository;
import com.task.vetclinic.Repository.PetRepository;
import com.task.vetclinic.Services.interfaces.PetService;
import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.PetDto;
import com.task.vetclinic.entities.Owner;
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
public class PetServiceImp implements PetService {
    private final PetRepository petRepository;
    private final ModelMapper mapper;
    private final OwnerRepository ownerRepository;

    public PetServiceImp(PetRepository petRepository,OwnerRepository ownerRepository){
        this.petRepository=petRepository;
        this.ownerRepository=ownerRepository;
        this.mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
    }

    @Override
    public SuccessResponse<?> createPet(Long ownerId, PetDto dto){
        Optional<Owner> optOwner=ownerRepository.findById(ownerId);
        if (optOwner.isEmpty())
            throw new CustomException(ResponseStringKeys.NOT_FOUND,ResponseIntegerKeys.NOT_FOUND);
        Pet pet=mapper.map(dto,Pet.class);
        pet.setOwner(optOwner.get());
        optOwner.get().getPets().add(pet);
        pet=petRepository.save(pet);
        return new SuccessResponse<>(ResponseStringKeys.CREATED,ResponseIntegerKeys.CREATED
                ,mapper.map(pet,PetDto.class));

    }
    @Override
    public SuccessResponse<?> getPet(Long id){
        return petRepository.findById(id).map(p->new SuccessResponse<>(ResponseStringKeys.OK,
                ResponseIntegerKeys.OK,mapper.map(p, PetDto.class))).orElse(
                new SuccessResponse<>(ResponseStringKeys.NOT_FOUND,ResponseIntegerKeys.NOT_FOUND));
    }
    @Override
    public SuccessResponse<?> uploadImage(Long petId, MultipartFile file) throws IOException {
        Optional<Pet> optPet=petRepository.findById(petId);
        if (optPet.isEmpty())
            throw new CustomException(ResponseStringKeys.NOT_FOUND,ResponseIntegerKeys.NOT_FOUND);
        optPet.get().setPhotos(ImageUtils.compressImage(file.getBytes()));
        petRepository.save(optPet.get());
        return new SuccessResponse<>(ResponseStringKeys.OK,ResponseIntegerKeys.OK);
    }

    @Override
    public byte[] downloadImage(Long petId) {
        Optional<Pet> optPet=petRepository.findById(petId);
        if (optPet.isEmpty())
            throw new CustomException(ResponseStringKeys.NOT_FOUND,ResponseIntegerKeys.NOT_FOUND);

        try {
            return ImageUtils.decompressImage(optPet.get().getPhotos());
        } catch (DataFormatException | IOException exception) {
            throw new CustomException(String.format("Error downloading an image with id %d",petId),ResponseIntegerKeys.DATA_INTEGRITY_VIOLATION);
        }
    }
}

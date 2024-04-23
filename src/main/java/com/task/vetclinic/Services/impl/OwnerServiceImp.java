package com.task.vetclinic.Services.impl;

import com.task.vetclinic.Repository.OwnerRepository;
import com.task.vetclinic.Services.interfaces.OwnerService;
import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.OwnerDto;
import com.task.vetclinic.dto.PetDto;
import com.task.vetclinic.entities.Owner;
import com.task.vetclinic.entities.Pet;
import com.task.vetclinic.exception.CustomException;
import com.task.vetclinic.util.ResponseIntegerKeys;
import com.task.vetclinic.util.ResponseStringKeys;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImp implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final ModelMapper mapper;
    public OwnerServiceImp(OwnerRepository ownerRepository){
        this.ownerRepository=ownerRepository;
        this.mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
    }
    @Override
    public SuccessResponse<?> createOwner(OwnerDto dto){
        Optional<Owner> optOwner=ownerRepository.findByName(dto.getName());
        if(optOwner.isPresent())
            throw new CustomException("this owner already exists", ResponseIntegerKeys.DATA_INTEGRITY_VIOLATION);
        Owner owner=mapper.map(dto,Owner.class);
        owner=ownerRepository.save(owner);
        return new SuccessResponse<>(ResponseStringKeys.CREATED,ResponseIntegerKeys.CREATED,
                mapper.map(owner,OwnerDto.class));
    }
    @Override
    public SuccessResponse<?> getOwner(Long id){

        Owner owner=validateOwner(id);

        return new SuccessResponse<>(ResponseStringKeys.OK,
                ResponseIntegerKeys.OK,mapper.map(owner, OwnerDto.class));
    }

    @Override
    public SuccessResponse<?> getPetListByOwnerId(Long id){

        Owner owner=validateOwner(id);

        List<Pet> pets=owner.getPets();
        if(pets.isEmpty())
            new SuccessResponse<>("there are no pets for this owner",ResponseIntegerKeys.NOT_FOUND);
        List<PetDto> petDtos =pets.stream()
                .map(d->mapper.map(d,PetDto.class)).toList();

        return new SuccessResponse<>(ResponseStringKeys.OK,ResponseIntegerKeys.OK,petDtos);
    }

    private Owner validateOwner(Long id){
        Optional<Owner> optOwner=ownerRepository.findById(id);
        if(optOwner.isEmpty())
            throw new CustomException("this Owner doesn't exit",ResponseIntegerKeys.NOT_FOUND);
        return optOwner.get();
    }
}

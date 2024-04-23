package com.task.vetclinic.Controller;

import com.task.vetclinic.Services.interfaces.OwnerService;
import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService service;

    @PostMapping("/create")
    public SuccessResponse<?> create(@RequestBody OwnerDto dto){
        return service.createOwner(dto);
    }
    @GetMapping("/get")
    public SuccessResponse<?> get(@RequestParam Long id){
        return service.getOwner(id);
    }
    @GetMapping("/getAllPets")
    public SuccessResponse<?> getAllPets(@RequestParam Long id){
        return service.getPetListByOwnerId(id);
    }

}

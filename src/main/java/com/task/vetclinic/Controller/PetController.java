package com.task.vetclinic.Controller;

import com.task.vetclinic.Services.interfaces.PetService;
import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.PetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService service;

    @PostMapping("/create")
    public SuccessResponse<?> create(@RequestParam Long ownerId ,@RequestBody PetDto dto){
        return service.createPet(ownerId,dto);
    }
    @GetMapping("/get")
    public SuccessResponse<?> get(@RequestParam Long id){
        return service.getPet(id);
    }
    @PutMapping("/uploadImage")
    public SuccessResponse<?> uploadImage(@RequestParam Long petId,
                                          @RequestParam("image") MultipartFile file)throws IOException {
        return service.uploadImage(petId,file);
    }
    @GetMapping("/downloadImage")
    public ResponseEntity<?> downloadImage(@RequestParam Long petId) {
        //return service.downloadImage(petId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(service.downloadImage(petId));
    }
}

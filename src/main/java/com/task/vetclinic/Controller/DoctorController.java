package com.task.vetclinic.Controller;

import com.task.vetclinic.Services.interfaces.DoctorService;
import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.DoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService service;

    @PostMapping("/create")
    public SuccessResponse<?> create(@RequestBody DoctorDto dto){
        return service.createDoctor(dto);
    }
    @GetMapping("/get")
    public SuccessResponse<?> get(@RequestParam Long id){
        return service.getDoctor(id);
    }
    @PutMapping("/uploadImage")
    public SuccessResponse<?> uploadImage(@RequestParam Long doctorId,
                                          @RequestParam("image")MultipartFile file)throws IOException {
        return service.uploadImage(doctorId,file);
    }
    @GetMapping("/downloadImage")
    public ResponseEntity<?> downloadImage(@RequestParam Long doctorId) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(service.downloadImage(doctorId));
    }

}

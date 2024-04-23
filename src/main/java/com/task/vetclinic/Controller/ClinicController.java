package com.task.vetclinic.Controller;

import com.task.vetclinic.Services.interfaces.ClinicService;
import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.AssignDeAssignDoctorRequest;
import com.task.vetclinic.dto.ClinicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clinic")
public class ClinicController {
    @Autowired
    private ClinicService service;

    @PostMapping("/create")
    public SuccessResponse<?> create(@RequestBody ClinicDto dto){
        return service.createClinic(dto);
    }
    @GetMapping("/get")
    public SuccessResponse<?> get(@RequestParam Long id){
        return service.getClinic(id);
    }
    @GetMapping("/getAllDoctors")
    public SuccessResponse<?> getAllDoctors(@RequestParam Long id){
        return service.getDoctorListByClinicId(id);
    }
    @GetMapping("/phoneAddressSearch")
    public SuccessResponse<?> search(@RequestParam String keyword){
        return service.searchClinicByPhoneOrAddress(keyword);
    }
    @PutMapping("/assignDoctor")
    public SuccessResponse<?> assignDoctor(@RequestBody AssignDeAssignDoctorRequest dto){
        return service.assignDoctor(dto);
    }
    @PutMapping("/deAssignDoctor")
    public SuccessResponse<?> deAssignDoctor(@RequestBody AssignDeAssignDoctorRequest dto){
        return service.deAssignDoctor(dto);
    }
}

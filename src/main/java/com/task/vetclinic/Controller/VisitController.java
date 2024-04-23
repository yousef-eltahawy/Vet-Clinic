package com.task.vetclinic.Controller;


import com.task.vetclinic.Services.interfaces.VisitService;
import com.task.vetclinic.customresponse.SuccessResponse;
import com.task.vetclinic.dto.VisitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitService service;

    @PostMapping("/create")
    public SuccessResponse<?> create(@RequestBody VisitDto dto){
        return service.createVisit(dto);
    }
    @GetMapping("/get")
    public SuccessResponse<?> get(@RequestParam Long id){
        return service.getVisit(id);
    }
}

package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Services;
import com.clinicmanagement.clinic.dto.ApiResponse;
import com.clinicmanagement.clinic.dto.ServiceDTO;
import com.clinicmanagement.clinic.service.ServicesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("/services")
    public List<Services> getAll(){
        return servicesService.getAll();
    }

    @GetMapping("/services/name/{name}")
    public Services services(@PathVariable String name){
        return servicesService.getByName(name);
    }

    @PostMapping("/services/new")
    public ApiResponse<Services> creatServices(@RequestBody @Valid ServiceDTO serviceDTO){
        ApiResponse<Services> apiResponse = new ApiResponse<>();
        apiResponse.setResult(servicesService.createServices(serviceDTO));
        return apiResponse;
    }

    @PostMapping("services/update/{id}")
    public Services updateService(@PathVariable int id,@RequestBody ServiceDTO serviceDTO){
        return servicesService.updateServices(id,serviceDTO);
    }

}

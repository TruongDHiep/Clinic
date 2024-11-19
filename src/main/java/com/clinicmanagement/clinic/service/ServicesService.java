package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Services;
import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.dto.ServiceDTO;
import com.clinicmanagement.clinic.exception.AppException;
import com.clinicmanagement.clinic.exception.ErrorCode;
import com.clinicmanagement.clinic.mapper.ServiceMapper;
import com.clinicmanagement.clinic.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceMapper serviceMapper;

    public List<Services> getAll(){
        return serviceRepository.findAll();
    }

    public Services getByID(int id){
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public Services getByName(String name) {
        Optional<Services> optionalUser = serviceRepository.findByserviceName(name);
        return optionalUser.orElseThrow(() -> new RuntimeException("Service not found"));
    }
    public Services createServices(ServiceDTO serviceRes){
        Services service = serviceMapper.toServices(serviceRes);
        return  serviceRepository.save(service);
    }
    public Services updateServices(int id,ServiceDTO serviceRes){
        Services services = getByID(id);
        serviceMapper.updateServices(serviceRes,services);
        return serviceRepository.save(services);
    }
}


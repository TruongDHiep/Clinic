package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Services;
import com.clinicmanagement.clinic.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository serviceRepository;

    public List<Services> getAllServices(){
        return serviceRepository.findAll();
    }

    public Services findById(Integer id){
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public Services findByServiceName(String serviceName){
        return serviceRepository.findByServiceName(serviceName);
    }

    public Optional<Services> findByServiceNameAndNotId(String serviceName, Integer id) {
        return serviceRepository.findByServiceNameAndNotId(serviceName,id);
    }

    public Services saveService(Services service){
        return  serviceRepository.save(service);
    }
}


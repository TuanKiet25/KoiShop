package com.example.demo.Service;

import com.example.demo.Entity.Store;
import com.example.demo.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;
    public com.example.demo.Entity.Service createNewService(com.example.demo.Entity.Service service){
        serviceRepository.save(service);
        return service;
    }
    public List<com.example.demo.Entity.Service> getAllService(){
        return serviceRepository.findAll();
    }
    public com.example.demo.Entity.Service deleteService(long id){
        com.example.demo.Entity.Service service= serviceRepository.findServiceById(id);
        service.setDeleted(true);
        return serviceRepository.save(service);
    }
}

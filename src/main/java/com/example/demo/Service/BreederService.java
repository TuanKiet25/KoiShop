package com.example.demo.Service;

import com.example.demo.Entity.Breeder;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.request.BreederRequest;
import com.example.demo.repository.BreederRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreederService {
    @Autowired
    BreederRepository breederRepository;

    public Breeder findBreederByName(String breederName){
        return breederRepository.findByBreederName(breederName);

    }
    public List<Breeder> getAllBreeder(){
        return breederRepository.findAll();

    }
    public Breeder createBreeder(BreederRequest breederRequest){
        Breeder breeder = new Breeder();
        List<Breeder> breederList = breederRepository.findAll();
        breeder.setBreederName(breederRequest.getBreederName());
        for(Breeder breeder1 : breederList){
            if(breeder1.getBreederName().equalsIgnoreCase(breeder.getBreederName())){
                throw new BadRequestException("Breeder already exist!!!");
            }
        }
        breeder.setBreederPhone(breederRequest.getBreederPhone());
        breeder.setBreederAdd(breederRequest.getBreederAdd());
        return breederRepository.save(breeder);
    }
}

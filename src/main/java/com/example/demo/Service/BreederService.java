package com.example.demo.Service;

import com.example.demo.Entity.Breeder;
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
}
